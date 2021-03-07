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
              {{ muServer.name }}
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
                {{ muServer.season }}
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
                <a
                  :href="muServer.website"
                  target="_blank"
                  class="hover:text-white"
                  >{{ muServer.website }}</a
                >
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
                {{ muServer.createdDate }}
              </div>
            </div>
          </div>
          <div v-if="true" class="mt-5 sm:mt-0 flex items-center space-x-4">
            <router-link :to="{ path: `/post/new?muServerId=${muServer.id}` }">
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
        @showAllPost="resetFilters"
        @muItemNameSelected="filterDataByItemName"
        @muItemCategoryNameSelected="filterDataByMuItemCategory"
        @postTypeNameSelected="filterDataByPostType"
        @userNicknameSelected="filterDataByUser"
      ></posts-filters>
    </section>
    <!-- Posts table -->
    <section
      v-if="posts != null"
      class="max-w-7xl mx-auto px-2 sm:px-6 lg:px-8 pb-8 lg:pb-12"
    >
      <posts :data="posts.data"></posts>
      <!-- Posts pagination -->
      <paginator
        :page="posts.description.number"
        :totalPages="posts.description.totalPages"
        @pageNumber="updatePage"
      ></paginator>
    </section>
  </default-layout>
</template>

<script>
import { mapGetters } from "vuex";
import DefaultLayout from "@/layouts/DefaultLayout.vue";
import PostsFilters from "@/components/PostsFilters.vue";
import Posts from "@/components/Posts.vue";
import Paginator from "@/components/pagination/Paginator.vue";

export default {
  data() {
    return {
      posts: null,
      currentPage: 0,
      pageSize: 6,
      postsUrl: "",
      muServerId: 0,
      muServer: {
        id: 0,
        name: "",
        season: "",
        website: "",
        createdDate: null,
      },
    };
  },
  computed: {
    ...mapGetters(["accessToken"]),
  },
  mounted() {
    this.init();
  },
  methods: {
    async fetchMuServerById() {
      try {
        const response = await fetch(
          `http://localhost:8088/api/mu-servers/${this.muServerId}`,
          {
            method: "GET",
            headers: {
              Authorization: this.accessToken,
            },
          }
        );

        this.muServer = response.status == 200 ? await response.json() : null;

        console.log(response);
      } catch (error) {
        console.error("MuServerPostsPage:fetchMuServerById:", error);
      }
    },
    updatePage(pageNumber) {
      this.currentPage = pageNumber;
      this.fetchMuServerPostsPage(
        `${this.postsUrl}&page=${this.currentPage}&size=${this.pageSize}`
      );
      // console.log("pageNumber", pageNumber);
      // console.log("currentPage", this.currentPage);
    },
    resetFilters() {
      this.currentPage = 0;
      this.postsUrl = `http://localhost:8088/api/posts/with-mu-server-id/${this.muServerId}?enabled=true`;
      this.updatePage(0);
    },
    filterDataByItemName(name) {
      this.currentPage = 0;
      this.postsUrl = `http://localhost:8088/api/posts/with-mu-server-id/${this.muServerId}?muItemName=${name}&enabled=true`;
      this.updatePage(0);
    },
    filterDataByMuItemCategory(name) {
      this.currentPage = 0;
      this.postsUrl = `http://localhost:8088/api/posts/with-mu-server-id/${this.muServerId}?muItemCategoryName=${name}&enabled=true`;
      this.updatePage(0);
    },
    filterDataByPostType(name) {
      this.currentPage = 0;
      this.postsUrl = `http://localhost:8088/api/posts/with-mu-server-id/${this.muServerId}?postTypeName=${name}&enabled=true`;
      this.updatePage(0);
    },
    filterDataByUser(nickname) {
      this.currentPage = 0;
      this.postsUrl = `http://localhost:8088/api/posts/with-mu-server-id/${this.muServerId}?userNickname=${nickname}&enabled=true`;
      this.updatePage(0);
    },
    async fetchMuServerPostsPage(url) {
      try {
        const response = await fetch(url, {
          method: "GET",
          headers: {
            Authorization: this.accessToken,
          },
        });

        this.posts = response.status == 200 ? await response.json() : null;

        console.log(response);
        console.info("Posts: ", this.posts);
      } catch (error) {
        console.error("MuServerPostsPage:fetchMuServerPostsPage:", error);
      }
    },
    init() {
      this.muServerId = this.$route.params.id;
      this.fetchMuServerById();
      this.postsUrl = `http://localhost:8088/api/posts/with-mu-server-id/${this.muServerId}?enabled=true`;
      this.updatePage(0);
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
