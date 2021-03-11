<template>
  <div
    @click="cancelOperation"
    class="flex w-full justify-center py-2 px-4 shadow font-medium rounded-md text-gray-900 border border-transparent hover:border-gray-900 cursor-pointer"
  >
    Cancel
  </div>
</template>

<script>
export default {
  methods: {
    cancelOperation() {
      this.$router.go(-1);
    },
  },
};
</script>