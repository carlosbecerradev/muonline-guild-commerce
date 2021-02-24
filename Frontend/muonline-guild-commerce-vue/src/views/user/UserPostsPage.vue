<template>
  <div class="">
    <default-layout>
      <!-- Title -->
      <section class="bg-gray-900">
        <div class="max-w-7xl mx-auto px-2 sm:px-6 lg:px-8 py-8 lg:py-12">
          <!-- This example requires Tailwind CSS v2.0+ -->
          <div class="sm:flex md:items-center md:justify-between">
            <div class="flex-1">
              <h2 class="font-extrabold text-yellow-300 text-4xl">
                {{ nickname }} Posts
              </h2>
            </div>
            <div v-if="true" class="mt-5 sm:mt-0 flex items-center space-x-4">
              <router-link :to="{ path: `/post/new` }">
                <div
                  class="flex items-center px-4 py-2 rounded-md shadow-sm font-medium text-gray-900 bg-yellow-300 hover:bg-yellow-400"
                >
                  <svg
                    class="-ml-2 h-5 w-3 mr-2"
                    width="12"
                    height="20"
                    fill="currentColor"
                  >
                    <path
                      fill-rule="evenodd"
                      clip-rule="evenodd"
                      d="M6 5a1 1 0 011 1v3h3a1 1 0 110 2H7v3a1 1 0 11-2 0v-3H2a1 1 0 110-2h3V6a1 1 0 011-1z"
                    />
                  </svg>
                  <span>Add Post</span>
                </div>
              </router-link>
            </div>
          </div>
        </div>
      </section>
    </default-layout>
  </div>
</template>

<script>
import DefaultLayout from "@/layouts/DefaultLayout.vue";
import PrimaryButton from "@/components/button/PrimaryButton.vue";
import SecondaryButton from "@/components/button/SecondaryButton.vue";

export default {
  data() {
    return {
      nickname: "Charspunk",
    };
  },
  components: {
    DefaultLayout,
    PrimaryButton,
    SecondaryButton,
  },
};
</script>
