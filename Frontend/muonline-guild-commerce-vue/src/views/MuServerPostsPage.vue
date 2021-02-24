<template>
  <default-layout class="">
    <!-- Mu Server -->
    <section v-if="true" class="bg-gray-900">
      <div class="max-w-7xl mx-auto px-2 sm:px-6 lg:px-8 py-8 lg:py-12">
        <!-- This example requires Tailwind CSS v2.0+ -->
        <div class="sm:flex md:items-center md:justify-between">
          <div class="flex-1">
            <h2 class="font-extrabold text-yellow-300 text-4xl">Posts</h2>
            <h3 class="text-2xl font-bold text-white sm:text-3xl sm:truncate">
              Mu Titanium Online
            </h3>
            <div class="mt-1 flex flex-wrap sm:flex-row">
              <div class="mt-2 mr-6 flex items-center text-gray-200">
                <!-- Heroicon name: briefcase -->
                <svg
                  class="flex-shrink-0 mr-1.5 h-5 w-5 text-gray-400"
                  xmlns="http://www.w3.org/2000/svg"
                  viewBox="0 0 20 20"
                  fill="currentColor"
                  aria-hidden="true"
                >
                  <path
                    fill-rule="evenodd"
                    d="M6 6V5a3 3 0 013-3h2a3 3 0 013 3v1h2a2 2 0 012 2v3.57A22.952 22.952 0 0110 13a22.95 22.95 0 01-8-1.43V8a2 2 0 012-2h2zm2-1a1 1 0 011-1h2a1 1 0 011 1v1H8V5zm1 5a1 1 0 011-1h.01a1 1 0 110 2H10a1 1 0 01-1-1z"
                    clip-rule="evenodd"
                  />
                  <path
                    d="M2 13.692V16a2 2 0 002 2h12a2 2 0 002-2v-2.308A24.974 24.974 0 0110 15c-2.796 0-5.487-.46-8-1.308z"
                  />
                </svg>
                Season 99b
              </div>
              <div class="mt-2 mr-6 flex items-center text-gray-200">
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
                <a href="" target="_blank" class="hover:text-white">Website</a>
              </div>
              <div class="mt-2 mr-6 flex items-center text-gray-200">
                <!-- Heroicon name: calendar -->
                <svg
                  class="flex-shrink-0 mr-1.5 h-5 w-5 text-gray-400"
                  xmlns="http://www.w3.org/2000/svg"
                  viewBox="0 0 20 20"
                  fill="currentColor"
                  aria-hidden="true"
                >
                  <path
                    fill-rule="evenodd"
                    d="M6 2a1 1 0 00-1 1v1H4a2 2 0 00-2 2v10a2 2 0 002 2h12a2 2 0 002-2V6a2 2 0 00-2-2h-1V3a1 1 0 10-2 0v1H7V3a1 1 0 00-1-1zm0 5a1 1 0 000 2h8a1 1 0 100-2H6z"
                    clip-rule="evenodd"
                  />
                </svg>
                Hace 10seg
              </div>
            </div>
          </div>
          <div v-if="true" class="mt-5 sm:mt-0 flex items-center space-x-4">
            <router-link :to="{ path: `/post/new?mu-server=1` }">
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

    <!-- Posts Filters -->
    <section class="max-w-7xl mx-auto px-2 sm:px-6 lg:px-8 py-6">
      <posts-filters
        @muItemNameSelected="filterDataByItemName"
        @filterSelected="filterDataBy"
      ></posts-filters>
    </section>
    <!-- Posts table -->
    <section class="max-w-7xl mx-auto px-2 sm:px-6 lg:px-8 pb-8 lg:pb-12">
      <posts :data="data"></posts>
      <!-- Posts pagination -->
      <paginator
        v-if="data != []"
        :page="4"
        :totalPages="12"
        @pageNumber="updatePage"
      ></paginator>
    </section>
  </default-layout>
</template>

<script>
import DefaultLayout from "@/layouts/DefaultLayout.vue";
import PostsFilters from "@/components/PostsFilters.vue";
import Posts from "@/components/Posts.vue";
import Paginator from "@/components/pagination/Paginator.vue";

export default {
  data() {
    return {
      data: [],
      currentPage: 0,
    };
  },
  mounted() {
    this.init();
  },
  methods: {
    updatePage(pageNumber) {
      this.currentPage = pageNumber;
      console.log("pageNumber", pageNumber);
      console.log("currentPage", this.currentPage);
    },
    filterDataByItemName(muItemNameSelected) {
      console.log("currentPage", this.currentPage);
      console.log("muItemNameSelected", muItemNameSelected);
    },
    filterDataBy(filterSelected) {
      console.log("currentPage", this.currentPage);
      console.log("filterSelected", filterSelected);
    },
    init() {
      this.currentPage = 0;
    },
  },
  components: {
    DefaultLayout,
    PostsFilters,
    Posts,
    Paginator,
  },
};
</script>
