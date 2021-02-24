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
          <div class="py-4 flex justify-around items-center">
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
          class="relative row-start-2 row-end-3 col-start-1 col-end-7 sm:row-span-1 sm:col-span-3 hover:bg-gray-200 cursor-pointer"
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
        <!-- Sort posts
        <div
          class="col-span-6 sm:col-start-10 hover:bg-gray-200 cursor-pointer"
        >
          <div
            @click="dropdowns.sortBy.show = !dropdowns.sortBy.show"
            class="relative py-4 flex justify-around items-center z-10"
          >
            <span>Sort By</span>
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

            <div
              v-if="dropdowns.sortBy.show"
              class="absolute top-full right-0 bg-gray-200 min-w-full max-h-40 w-auto overflow-auto"
            >
              <div
                v-for="(item, index) in dropdowns.sortBy.items"
                :key="index"
                class="text-gray-900 px-6 py-2 hover:bg-gray-300 whitespace-nowrap"
              >
                {{ item }}
              </div>
            </div>
          </div>
        </div>
        -->
      </div>

      <!-- Filters-->
      <div
        v-if="dropdowns.filters.show"
        class="absolute top-full left-0 bg-gray-200 w-full"
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
                  @click="emitFilterSelected('item-category', item)"
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
                  @click="emitFilterSelected('post-type', item)"
                  v-for="item in dropdowns.postTypeFilter.items"
                  :key="item.id"
                  class="text-gray-900 px-6 py-2 hover:bg-gray-300 whitespace-nowrap"
                >
                  {{ item.name }}
                </div>
              </div>
            </div>
          </div>
          <!-- Filter by User -->
          <div
            class="col-span-6 sm:col-span-3 hover:bg-gray-200 cursor-pointer"
          >
            <div
              @click="openUserFilterDropdown"
              class="relative py-4 flex justify-around items-center"
            >
              <span>User</span>
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
                v-if="dropdowns.userFilter.show"
                class="absolute top-full left-0 bg-gray-200 max-h-40 min-w-full w-auto overflow-auto z-20"
              >
                <div
                  @click="emitFilterSelected('user', item)"
                  v-for="item in dropdowns.userFilter.items"
                  :key="item.id"
                  class="text-gray-900 px-6 py-2 hover:bg-gray-300 whitespace-nowrap"
                >
                  {{ item.nickname }}
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
import Search from "@/components/filter/Search.vue";

export default {
  data() {
    return {
      searchItems: [],
      dropdowns: {
        sortBy: {
          show: false,
          items: ["Created Date"],
        },
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
        userFilter: {
          show: false,
          items: [],
        },
      },
    };
  },
  mounted() {
    this.init();
  },
  methods: {
    fetchMuItemsByName(name) {
      console.log("searchInputText", name);
      if (name != "") {
        this.searchItems = ["A", "B", "C", "D", "E", "F"];
      }
    },
    emitMuItemNameSelected(name) {
      this.$emit("muItemNameSelected", name);
      console.log("searchItemSelected", name);
    },
    emitFilterSelected(filter, id) {
      this.$emit("filterSelected", { filter, id });
      this.resetDropdownsShows();
      console.log("filterSelected", { filter, id });
    },
    fetchMuItemCategories() {
      this.dropdowns.itemCategoryFilter.items = [
        { id: 1, name: "Wings" },
        { id: 2, name: "Lucky" },
        { id: 3, name: "Ancient" },
      ];
    },
    fetchPostTypes() {
      this.dropdowns.postTypeFilter.items = [
        { id: 1, name: "Sell" },
        { id: 2, name: "Buy" },
      ];
    },
    fetchUsers() {
      this.dropdowns.userFilter.items = [
        { id: 1, nickname: "Chars" },
        { id: 2, nickname: "Sybil" },
      ];
    },
    init() {
      this.fetchUsers();
      this.fetchMuItemCategories();
      this.fetchPostTypes();
    },
    resetDropdownsShows() {
      // this.dropdowns.sortBy.show = false
      this.dropdowns.filters.show = false;
      this.dropdowns.itemCategoryFilter.show = false;
      this.dropdowns.postTypeFilter.show = false;
      this.dropdowns.userFilter.show = false;
    },
    openItemCategoryFilterDropdown() {
      this.dropdowns.itemCategoryFilter.show = !this.dropdowns
        .itemCategoryFilter.show;
      this.dropdowns.postTypeFilter.show = false;
      this.dropdowns.userFilter.show = false;
    },
    openPostTypeFilterDropdown() {
      this.dropdowns.itemCategoryFilter.show = false;
      this.dropdowns.postTypeFilter.show = !this.dropdowns.postTypeFilter.show;
      this.dropdowns.userFilter.show = false;
    },
    openUserFilterDropdown() {
      this.dropdowns.itemCategoryFilter.show = false;
      this.dropdowns.postTypeFilter.show = false;
      this.dropdowns.userFilter.show = !this.dropdowns.userFilter.show;
    },
  },
  components: {
    Search,
  },
};
</script>
