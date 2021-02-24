<template>
  <default-layout>
    <!-- This example requires Tailwind CSS v2.0+ -->
    <section class="bg-gray-900">
      <div
        class="max-w-7xl mx-auto py-8 lg:py-12 px-4 sm:px-6 lg:px-8 lg:flex lg:items-center lg:justify-between"
      >
        <div class="flex-1">
          <h2 class="font-extrabold text-yellow-300 text-4xl">
            MU Servers
            <span class="block text-2xl text-white">We are there.</span>
          </h2>
        </div>
      </div>
    </section>

    <section v-if="true" class="divide-y bg-white">
      <div v-for="i in 3" :key="i" class="max-w-7xl mx-auto px-4 md:px-8 py-6">
        <!-- This example requires Tailwind CSS v2.0+ -->
        <div class="md:flex md:items-center md:justify-between text-md">
          <div class="flex-1">
            <h2
              class="text-2xl font-bold sm:text-3xl sm:truncate text-gray-900"
            >
              MU PVP
            </h2>
            <div
              class="mt-1 flex flex-col sm:flex-row sm:flex-wrap sm:mt-0 sm:space-x-6"
            >
              <div class="mt-2 flex items-center text-gray-600">
                <!-- Heroicon name: briefcase -->
                <svg
                  class="flex-shrink-0 mr-1.5 h-5 w-5 text-gray-400"
                  xmlns="http://www.w3.org/2000/svg"
                  fill="none"
                  viewBox="0 0 24 24"
                  stroke="currentColor"
                >
                  <path
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    stroke-width="2"
                    d="M13 16h-1v-4h-1m1-4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"
                  />
                </svg>
                <span>Season 6</span>
              </div>
              <div class="mt-2 flex items-center text-gray-600">
                <!-- Heroicon name: location-marker -->
                <svg
                  class="flex-shrink-0 mr-1.5 h-5 w-5 text-gray-400"
                  xmlns="http://www.w3.org/2000/svg"
                  viewBox="0 0 20 20"
                  fill="currentColor"
                >
                  <path
                    fill-rule="evenodd"
                    d="M12.586 4.586a2 2 0 112.828 2.828l-3 3a2 2 0 01-2.828 0 1 1 0 00-1.414 1.414 4 4 0 005.656 0l3-3a4 4 0 00-5.656-5.656l-1.5 1.5a1 1 0 101.414 1.414l1.5-1.5zm-5 5a2 2 0 012.828 0 1 1 0 101.414-1.414 4 4 0 00-5.656 0l-3 3a4 4 0 105.656 5.656l1.5-1.5a1 1 0 10-1.414-1.414l-1.5 1.5a2 2 0 11-2.828-2.828l3-3z"
                    clip-rule="evenodd"
                  />
                </svg>
                <a href="" target="_blank">website</a>
              </div>
              <div class="mt-2 flex items-center text-gray-600">
                <!-- Heroicon name: calendar -->
                <svg
                  class="flex-shrink-0 mr-1.5 h-5 w-5 text-gray-400"
                  xmlns="http://www.w3.org/2000/svg"
                  fill="none"
                  viewBox="0 0 24 24"
                  stroke="currentColor"
                >
                  <path
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    stroke-width="2"
                    d="M8 7V3m8 4V3m-9 8h10M5 21h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z"
                  />
                </svg>
                hace 1 año
              </div>
            </div>
          </div>
          <div class="mt-5 flex md:mt-0 md:ml-4 space-x-4">
            <router-link :to="{ path: `/post/new?mu-server=1` }">
              <div
                class="flex items-center px-4 py-2 rounded-md shadow-sm font-medium text-gray-900 bg-yellow-300 hover:bg-yellow-400"
              >
                <svg
                  class="-ml-1 h-5 w-5"
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
            <router-link :to="{ path: `/mu-server/1/posts` }">
              <div
                class="flex items-center px-4 py-2 rounded-md shadow-sm font-medium text-gray-900 bg-yellow-300 hover:bg-yellow-400"
              >
                <svg
                  class="-ml-1 mr-2 h-5 w-5"
                  xmlns="http://www.w3.org/2000/svg"
                  viewBox="0 0 20 20"
                  fill="currentColor"
                >
                  <path d="M10 12a2 2 0 100-4 2 2 0 000 4z" />
                  <path
                    fill-rule="evenodd"
                    d="M.458 10C1.732 5.943 5.522 3 10 3s8.268 2.943 9.542 7c-1.274 4.057-5.064 7-9.542 7S1.732 14.057.458 10zM14 10a4 4 0 11-8 0 4 4 0 018 0z"
                    clip-rule="evenodd"
                  />
                </svg>
                <span>View Posts</span>
              </div>
            </router-link>
          </div>
        </div>
      </div>
    </section>
  </default-layout>
</template>

<script>
import DefaultLayout from "@/layouts/DefaultLayout.vue";

export default {
  components: {
    DefaultLayout,
  },
};
</script>
