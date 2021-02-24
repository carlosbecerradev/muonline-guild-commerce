<template>
  <button
    type="submit"
    class="flex w-full justify-center py-2 px-4 shadow font-medium rounded-md text-gray-900 border border-transparent hover:border-gray-900"
  >
    {{ text }}
  </button>
</template>

<script>
export default {
  props: {
    text: {
      type: String,
      default: "Submit",
    },
  },
};
</script>
