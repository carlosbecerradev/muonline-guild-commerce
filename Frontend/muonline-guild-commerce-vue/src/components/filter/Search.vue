<template>
  <!-- Seacth filter -->
  <div class="relative flex bg-white px-4 items-center border border-b-0 z-20">
    <!-- Search icon-->
    <div class="pr-3">
      <svg
        class="h-6"
        xmlns="http://www.w3.org/2000/svg"
        fill="none"
        viewBox="0 0 24 24"
        stroke="currentColor"
      >
        <path
          stroke-linecap="round"
          stroke-linejoin="round"
          stroke-width="2"
          d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"
        />
      </svg>
    </div>
    <!-- Autocomplete-->
    <input
      @input="sendInputText"
      v-model="text"
      :placeholder="placeholder"
      class="flex-auto py-4 text-base leading-6 text-gray-500 placeholder-gray-500 focus:outline-none focus:placeholder-gray-400"
      type="text"
    />
    <!-- Autocomplete List-->
    <div v-if="items != [] && text != ''">
      <div
        class="absolute top-full left-0 bg-gray-200 px-1 w-full max-h-40 overflow-y-auto"
      >
        <div
          @click="sendItemSelected(item)"
          v-for="(item, index) in items"
          :key="index"
          class="cursor-pointer text-sm text-gray-900 pl-12 py-2 hover:bg-gray-300"
        >
          {{ item }}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      text: "",
    };
  },
  props: {
    placeholder: {
      type: String,
      default: "Write some text",
    },
    items: {
      type: Array,
      default: [],
    },
  },
  methods: {
    sendInputText() {
      if (this.text.trim() != "") {
        this.$emit("inputText", this.text.trim());
      } else {
        this.cleanInputText();
      }
      // console.log("inputText", this.text, " - ", this.text.trim());
    },
    sendItemSelected(item) {
      this.$emit("itemSelected", item);
      this.cleanInputText();
      // console.log("itemSelected", item);
    },
    cleanInputText() {
      this.text = "";
    },
  },
};
</script>
