<template>
  <div v-if="page >= 0" class="flex justify-center items-center py-6 gap-x-2">
    <div
      v-if="showPreviousPage()"
      @click="changePage(page - 1)"
      class="cursor-pointer rounded hover:bg-gray-200"
    >
      <div class="text-sm p-1">
        <svg
          class="h-4 w-5"
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
    </div>
    <div
      @click="changePage(i - 1)"
      v-for="i in generatePagesArray()"
      :key="i"
      class="flex items-center cursor-pointer rounded hover:bg-gray-200"
      :class="{ 'bg-yellow-200': i == page + 1 }"
    >
      <div class="px-2 text-sm">{{ i }}</div>
    </div>
    <div
      v-if="showNextPage()"
      @click="changePage(page + 1)"
      class="cursor-pointer rounded hover:bg-gray-200"
    >
      <div class="text-sm p-1">
        <svg
          class="h-4 w-5"
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
    </div>
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
      var shownPages = 3;
      var result = [];
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
