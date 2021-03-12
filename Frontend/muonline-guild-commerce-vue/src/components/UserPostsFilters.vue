<template>
  <div class="bg-gray-100">
    <search
      :placeholder="'Search posts by mu item name selected'"
      :items="searchItems"
      @inputText="fetchMuItemsByName"
      @itemSelected="emitMuItemNameSelected"
    />

    <!-- Shows-->
    <div class="relative">
      <div class="grid grid-cols-12">
        <div
          class="relative col-span-6 sm:col-span-3 hover:bg-gray-200 cursor-pointer"
        >
          <!-- Show all posts-->
          <div
            @click="emitAllPostsOption"
            class="py-4 flex justify-around items-center"
          >
            <span>All Posts</span>
            <svg
              class="h-4 w-5"
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 24 24"
              stroke="currentColor"
            >
              <path
                stroke-linecap="round"
                stroke-linejoin="round"
                stroke-width="2"
                d="M19 9l-7 7-7-7"
              />
            </svg>
          </div>
        </div>
        <!-- Show other filters-->
        <div
          class="relative col-span-6 sm:col-span-3 hover:bg-gray-200 cursor-pointer"
        >
          <div
            @click="dropdowns.filters.show = !dropdowns.filters.show"
            class="py-4 flex justify-around items-center"
          >
            <span>Filters</span>
            <svg class="h-5 w-3" width="12" height="20" fill="currentColor">
              <path
                fill-rule="evenodd"
                clip-rule="evenodd"
                d="M6 5a1 1 0 011 1v3h3a1 1 0 110 2H7v3a1 1 0 11-2 0v-3H2a1 1 0 110-2h3V6a1 1 0 011-1z"
              />
            </svg>
          </div>
        </div>
      </div>

      <!-- Filters-->
      <div
        v-if="dropdowns.filters.show"
        class="absolute top-full left-0 bg-gray-200 w-full z-10"
      >
        <div class="grid grid-cols-12">
          <!-- Filter by Item Category -->
          <div
            class="col-span-6 sm:col-span-3 hover:bg-gray-200 cursor-pointer"
          >
            <div
              @click="openItemCategoryFilterDropdown"
              class="relative py-4 flex justify-around items-center"
            >
              <span>Item Category</span>
              <svg
                class="h-4 w-5"
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                stroke="currentColor"
              >
                <path
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  stroke-width="2"
                  d="M19 9l-7 7-7-7"
                />
              </svg>

              <!-- Filter by item category-->
              <div
                v-if="dropdowns.itemCategoryFilter.show"
                class="absolute top-full left-0 bg-gray-200 max-h-40 min-w-full w-auto overflow-auto z-20"
              >
                <div
                  @click="emitMuItemCategoryFilterSelected(item.name)"
                  v-for="item in dropdowns.itemCategoryFilter.items"
                  :key="item.id"
                  class="text-gray-900 px-6 py-2 hover:bg-gray-300 whitespace-nowrap"
                >
                  {{ item.name }}
                </div>
              </div>
            </div>
          </div>
          <!-- Filter by Post Tyoe -->
          <div
            class="col-span-6 sm:col-span-3 hover:bg-gray-200 cursor-pointer"
          >
            <div
              @click="openPostTypeFilterDropdown"
              class="relative py-4 flex justify-around items-center"
            >
              <span>Post Tyoe</span>
              <svg
                class="h-4 w-5"
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                stroke="currentColor"
              >
                <path
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  stroke-width="2"
                  d="M19 9l-7 7-7-7"
                />
              </svg>

              <!-- Filter by post type-->
              <div
                v-if="dropdowns.postTypeFilter.show"
                class="absolute top-full left-0 bg-gray-200 max-h-40 min-w-full w-auto overflow-auto z-20"
              >
                <div
                  @click="emitPostTypeFilterSelected(item.name)"
                  v-for="item in dropdowns.postTypeFilter.items"
                  :key="item.id"
                  class="text-gray-900 px-6 py-2 hover:bg-gray-300 whitespace-nowrap"
                >
                  {{ item.name }}
                </div>
              </div>
            </div>
          </div>
          <!-- Filter by Mu server -->
          <div
            class="col-span-6 sm:col-span-3 hover:bg-gray-200 cursor-pointer"
          >
            <div
              @click="openMuServerFilterDropdown"
              class="relative py-4 flex justify-around items-center"
            >
              <span>Mu Server</span>
              <svg
                class="h-4 w-5"
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                stroke="currentColor"
              >
                <path
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  stroke-width="2"
                  d="M19 9l-7 7-7-7"
                />
              </svg>

              <!-- Filter by Mu server-->
              <div
                v-if="dropdowns.muServerFilter.show"
                class="absolute top-full left-0 bg-gray-200 max-h-40 min-w-full w-auto overflow-auto z-20"
              >
                <div
                  @click="emitMuServerFilterSelected(item.name)"
                  v-for="item in dropdowns.muServerFilter.items"
                  :key="item.id"
                  class="text-gray-900 px-6 py-2 hover:bg-gray-300 whitespace-nowrap"
                >
                  {{ item.name }}
                </div>
              </div>
            </div>
          </div>
          <!-- Filter by enabled -->
          <div
            class="col-span-6 sm:col-span-3 hover:bg-gray-200 cursor-pointer"
          >
            <div
              @click="openEnabledFilterDropdown"
              class="relative py-4 flex justify-around items-center"
            >
              <span>Enabled</span>
              <svg
                class="h-4 w-5"
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                stroke="currentColor"
              >
                <path
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  stroke-width="2"
                  d="M19 9l-7 7-7-7"
                />
              </svg>

              <!-- Filter by enabled-->
              <div
                v-if="dropdowns.enabledFilter.show"
                class="absolute top-full left-0 bg-gray-200 max-h-40 min-w-full w-auto overflow-auto z-20"
              >
                <div
                  @click="emitEnabledFilterSelected('true')"
                  class="text-gray-900 px-6 py-2 hover:bg-gray-300 whitespace-nowrap"
                >
                  Uncompleted
                </div>
                <div
                  @click="emitEnabledFilterSelected('false')"
                  class="text-gray-900 px-6 py-2 hover:bg-gray-300 whitespace-nowrap"
                >
                  Completed
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import Search from "@/components/filter/Search.vue";

export default {
  data() {
    return {
      searchItems: [],
      dropdowns: {
        filters: {
          show: false,
          items: ["Created Date"],
        },
        itemCategoryFilter: {
          show: false,
          items: [],
        },
        postTypeFilter: {
          show: false,
          items: [],
        },
        muServerFilter: {
          show: false,
          items: [],
        },
        enabledFilter: {
          show: false,
          items: [],
        },
      },
    };
  },
  mounted() {
    this.init();
  },
  computed: {
    ...mapGetters(["accessToken"]),
  },
  methods: {
    emitAllPostsOption() {
      this.$emit("showAllPost");
      console.log("emitAllPostsOption", "Show All Posts");
      this.resetDropdownsShows();
    },
    emitMuItemNameSelected(name) {
      this.$emit("muItemNameSelected", name);
      console.log("emitMuItemNameSelected", name);
      this.resetDropdownsShows();
    },
    emitMuItemCategoryFilterSelected(name) {
      this.$emit("muItemCategoryNameSelected", name);
      console.log("emitMuItemCategoryFilterSelected", name);
      this.resetDropdownsShows();
    },
    emitPostTypeFilterSelected(name) {
      this.$emit("postTypeNameSelected", name);
      console.log("emitPostTypeFilterSelected", name);
      this.resetDropdownsShows();
    },
    emitMuServerFilterSelected(name) {
      this.$emit("muServerNameSelected", name);
      console.log("emitMuServerFilterSelected", name);
      this.resetDropdownsShows();
    },
    emitEnabledFilterSelected(value) {
      this.$emit("enabledValueSelected", value);
      console.log("emitEnabledFilterSelected", value);
      this.resetDropdownsShows();
    },
    async fetchMuItemsByName(name) {
      console.log("searchInputText", name);
      if (name.length > 0) {
        try {
          const response = await fetch(
            `http://localhost:8088/api/mu-items/contain?name=${name}`,
            {
              method: "GET",
              headers: {
                Authorization: this.accessToken,
              },
            }
          );

          this.searchItems =
            response.status == 200
              ? (await response.json()).map((muItem) => muItem.name)
              : [];
          console.log(response);
        } catch (error) {
          console.error("UserPostsFilters:fetchMuItemsByName:", error);
        }
      }
    },
    async fetchMuItemCategories() {
      try {
        const response = await fetch(
          `http://localhost:8088/api/mu-item-categories/list`,
          {
            method: "GET",
            headers: {
              Authorization: this.accessToken,
            },
          }
        );

        this.dropdowns.itemCategoryFilter.items =
          response.status == 200 ? await response.json() : [];
        console.log(response);
        console.log(this.dropdowns.itemCategoryFilter.items);
      } catch (error) {
        console.error("UserPostsFilters:fetchMuItemCategories:", error);
      }
    },
    async fetchPostTypes() {
      try {
        const response = await fetch(
          `http://localhost:8088/api/post-types/list`,
          {
            method: "GET",
            headers: {
              Authorization: this.accessToken,
            },
          }
        );

        this.dropdowns.postTypeFilter.items =
          response.status == 200 ? await response.json() : [];
        console.log(response);
      } catch (error) {
        console.error("UserPostsFilters:fetchPostTypes:", error);
      }
    },
    async fetchMuServers() {
      try {
        const response = await fetch(
          `http://localhost:8088/api/mu-servers/list`,
          {
            method: "GET",
            headers: {
              Authorization: this.accessToken,
            },
          }
        );

        this.dropdowns.muServerFilter.items =
          response.status == 200 ? await response.json() : [];
        console.log(response);
      } catch (error) {
        console.error("UserPostsFilters:fetchMuServers:", error);
      }
    },
    init() {
      this.fetchMuServers();
      this.fetchMuItemCategories();
      this.fetchPostTypes();
    },
    resetDropdownsShows() {
      this.dropdowns.filters.show = false;
      this.dropdowns.itemCategoryFilter.show = false;
      this.dropdowns.postTypeFilter.show = false;
      this.dropdowns.muServerFilter.show = false;
      this.dropdowns.enabledFilter.show = false;
    },
    openItemCategoryFilterDropdown() {
      this.dropdowns.itemCategoryFilter.show = !this.dropdowns
        .itemCategoryFilter.show;
      this.dropdowns.postTypeFilter.show = false;
      this.dropdowns.muServerFilter.show = false;
      this.dropdowns.enabledFilter.show = false;
    },
    openPostTypeFilterDropdown() {
      this.dropdowns.itemCategoryFilter.show = false;
      this.dropdowns.postTypeFilter.show = !this.dropdowns.postTypeFilter.show;
      this.dropdowns.muServerFilter.show = false;
      this.dropdowns.enabledFilter.show = false;
    },
    openMuServerFilterDropdown() {
      this.dropdowns.itemCategoryFilter.show = false;
      this.dropdowns.postTypeFilter.show = false;
      this.dropdowns.muServerFilter.show = !this.dropdowns.muServerFilter.show;
      this.dropdowns.enabledFilter.show = false;
    },
    openEnabledFilterDropdown() {
      this.dropdowns.itemCategoryFilter.show = false;
      this.dropdowns.postTypeFilter.show = false;
      this.dropdowns.muServerFilter.show = false;
      this.dropdowns.enabledFilter.show = !this.dropdowns.enabledFilter.show;
    },
  },
  components: {
    Search,
  },
};
</script>
