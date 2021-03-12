<template>
  <div class="">
    <default-layout>
      <!-- Title -->
      <section class="bg-gray-900">
        <div class="max-w-7xl mx-auto px-2 sm:px-6 lg:px-8 py-8 lg:py-12">
          <!-- This example requires Tailwind CSS v2.0+ -->
          <div class="sm:flex md:items-center md:justify-between">
            <div v-if="loggedInData.status" class="flex-1">
              <h2 class="font-extrabold text-yellow-300 text-4xl">
                {{ loggedInData.nickname }} Posts
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

      <section
        v-if="userPosts != null"
        class="max-w-7xl mx-auto px-2 sm:px-6 lg:px-8 pb-8 lg:pb-12"
      >
        <user-posts :data="userPosts.data"></user-posts>
        <!-- Posts pagination -->
        <paginator
          :page="userPosts.description.number"
          :totalPages="userPosts.description.totalPages"
          @pageNumber="updatePage"
        ></paginator>
      </section>
    </default-layout>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import DefaultLayout from "@/layouts/DefaultLayout.vue";
import PrimaryButton from "@/components/button/PrimaryButton.vue";
import SecondaryButton from "@/components/button/SecondaryButton.vue";
import UserPosts from "@/components/UserPosts.vue";
import Paginator from "@/components/pagination/Paginator.vue";

export default {
  data() {
    return {
      userPosts: null,
      currentPage: 0,
      pageSize: 6,
      userPostsUrl: "",
    };
  },
  computed: {
    ...mapGetters(["accessToken", "loggedInData"]),
  },
  methods: {
    async fetchUserPostsPage(url) {
      try {
        const response = await fetch(url, {
          method: "GET",
          headers: {
            Authorization: this.accessToken,
          },
        });

        this.userPosts = response.status == 200 ? await response.json() : null;

        console.log(response);
        console.info("userPosts: ", this.userPosts);
      } catch (error) {
        console.error("fetchUserPostsPage:fetchUserPostsPage:", error);
      }
    },
    updatePage(pageNumber) {
      this.currentPage = pageNumber;
      this.fetchUserPostsPage(
        `${this.userPostsUrl}&page=${this.currentPage}&size=${this.pageSize}`
      );
      // console.log("pageNumber", pageNumber);
      // console.log("currentPage", this.currentPage);
    },
    init() {
      this.userPostsUrl = `http://localhost:8088/api/users/posts?enabled=true`;
      this.updatePage(0);
    },
  },
  mounted() {
    this.init();
  },
  components: {
    DefaultLayout,
    PrimaryButton,
    SecondaryButton,
    UserPosts,
    Paginator,
  },
};
</script>
