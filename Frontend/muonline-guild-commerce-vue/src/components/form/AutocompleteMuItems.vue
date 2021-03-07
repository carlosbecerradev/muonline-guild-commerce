<template>
  <div class="relative">
    <input
      @input="sendInputText"
      v-model="text"
      :placeholder="placeholder"
      class="mt-1 block w-full py-2 px-3 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-gray-900 text-sm"
      type="text"
    />
    <!-- Autocomplete List-->
    <div v-if="showAutocompleteLayer && text != ''">
      <div
        class="absolute top-full left-0 border border-gray-900 bg-white w-full max-h-40 overflow-y-auto"
      >
        <div
          @click="sendItemSelected(item)"
          v-for="(item, index) in items"
          :key="index"
          class="px-3 cursor-pointer text-sm text-gray-900 py-1 hover:bg-gray-200"
        >
          {{ item.name }}
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
      showAutocompleteLayer: false,
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
        this.showAutocompleteLayer = true;
      } else {
        this.cleanInputText();
      }
      // console.log("inputText", this.text, " - ", this.text.trim());
    },
    sendItemSelected(item) {
      this.$emit("itemSelected", item);
      this.text = item.name;
      this.showAutocompleteLayer = false;
      // console.log("itemSelected", item);
    },
    cleanInputText() {
      this.text = "";
    },
  },
};
</script>
