<template>
  <div class="min-h-screen bg-gray-900">
    <header-partial />
    <div class="flex items-center justify-center py-12 sm:px-6 lg:px-8">
      <slot />
    </div>
  </div>
</template>

<script>
import HeaderPartial from "@/partials/HeaderPartial.vue";

export default {
  components: {
    HeaderPartial,
  },
};
</script>
