<template>
  <button
    type="submit"
    class="flex w-full justify-center py-2 px-4 border border-transparent shadow font-medium rounded-md text-gray-900 bg-yellow-300 hover:bg-yellow-400 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
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
