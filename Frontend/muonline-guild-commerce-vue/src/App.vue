<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
import { mapActions } from "vuex";

export default {
  methods: {
    ...mapActions(["loadAccessToken"]),
  },
  mounted() {
    this.loadAccessToken();
  },
};
</script>
