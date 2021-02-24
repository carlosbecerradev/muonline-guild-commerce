<template>
  <label class="block font-medium text-gray-900">{{ text }}</label>
</template>

<script>
export default {
  props: {
    text: {
      type: String,
      default: "",
    },
  },
};
</script>
