<template>
  <!-- Default Layout -->
  <div class="min-h-screen flex flex-col">
    <header-partial />
    <main class="main">
      <slot />
    </main>
    <footer-partial />
  </div>
</template>

<script>
import HeaderPartial from "@/partials/HeaderPartial.vue";
import FooterPartial from "@/partials/FooterPartial.vue";

export default {
  components: {
    HeaderPartial,
    FooterPartial,
  },
};
</script>
