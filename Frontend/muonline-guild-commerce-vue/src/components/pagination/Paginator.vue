<template>
  <div v-if="page >= 0 && totalPages > 1" class="flex justify-center items-center py-6 gap-x-2">
    <button
      v-if="showPreviousPage()"
      @click="changePage(page - 1)"
      class="rounded hover:bg-gray-200"
    >
      <div class="px-1 py-1">
        <svg
          class="h-5 w-6"
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          viewBox="0 0 24 24"
          stroke="currentColor"
        >
          <path
            stroke-linecap="round"
            stroke-linejoin="round"
            stroke-width="2"
            d="M15 19l-7-7 7-7"
          />
        </svg>
      </div>
    </button>
    <button
      @click="changePage(i - 1)"
      v-for="i in generatePagesArray()"
      :key="i"
      class="flex items-center rounded hover:bg-gray-200"
      :class="{ 'bg-gray-900 hover:bg-gray-900 text-gray-200': i == page + 1 }"
      :disabled="i == page + 1"
    >
      <div class="px-3 py-1 text-sm">{{ i }}</div>
    </button>
    <button
      v-if="showNextPage()"
      @click="changePage(page + 1)"
      class="rounded hover:bg-gray-200"
    >
      <div class="px-1 py-1">
        <svg
          class="h-5 w-6"
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          viewBox="0 0 24 24"
          stroke="currentColor"
        >
          <path
            stroke-linecap="round"
            stroke-linejoin="round"
            stroke-width="2"
            d="M9 5l7 7-7 7"
          />
        </svg>
      </div>
    </button>
  </div>
</template>

<script>
export default {
  props: {
    page: {
      type: Number,
      required: true,
    },
    totalPages: {
      type: Number,
      required: true,
    },
  },
  methods: {
    changePage(pageNumber) {
      if (pageNumber >= 0 && pageNumber < this.totalPages) {
        console.log("page", this.page);
        this.$emit("pageNumber", pageNumber);
      }
    },
    showPreviousPage() {
      return this.page == 0 ? false : true;
    },
    showNextPage() {
      return this.page == this.totalPages - 1 ? false : true;
    },
    generatePagesArray() {
      var shownPages = 4;
      var result = [];

      if (this.totalPages <= 3) {
        let i = 1;
        while (i != this.totalPages + 1) {
          result.push(i);
          i++;
        }
        return result;
      }

      if (this.page > this.totalPages - shownPages) {
        result.push(this.totalPages - 2, this.totalPages - 1, this.totalPages);
      } else {
        result.push(
          this.page + 1,
          this.page + 2,
          this.page + 3,
          this.totalPages
        );
      }
      if (this.page > 0) {
        result.unshift(1);
      }
      console.log("generatePagesArray():", result);
      return result;
    },
  },
};
</script>
