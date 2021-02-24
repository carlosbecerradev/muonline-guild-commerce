<template>
  <nav class="w-full bg-gray-900 z-10">
    <!-- Overlay -->
    <div
      v-if="dropdown"
      @click="dropdown = !dropdown"
      class="fixed inset-0 w-full h-full bg-black opacity-40"
      tabindex="-1"
    ></div>
    <!-- Navigation bars-->
    <div class="max-w-7xl mx-auto px-2 sm:px-6 lg:px-8">
      <div
        class="relative flex items-center justify-between h-20 border-b border-gray-800"
      >
        <!-- Mobile menu button-->
        <div class="absolute inset-y-0 left-0 flex items-center sm:hidden">
          <button
            @click="menuDropdown = !menuDropdown"
            class="inline-flex items-center justify-center p-2 rounded-md text-gray-400 hover:text-yellow-400 hover:bg-gray-900 focus:outline-none"
            aria-expanded="false"
          >
            <span class="sr-only">Open main menu</span>
            <!-- Icon when menu is closed. -->
            <!-- Heroicon name: menu
              Menu open: "hidden", Menu closed: "block" -->
            <svg
              v-if="!menuDropdown"
              class="block h-6 w-6"
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 24 24"
              stroke="currentColor"
              aria-hidden="true"
            >
              <path
                stroke-linecap="round"
                stroke-linejoin="round"
                stroke-width="2"
                d="M4 6h16M4 12h16M4 18h16"
              />
            </svg>
            <!-- Icon when menu is open. -->
            <!--
              Heroicon name: x

              Menu open: "block", Menu closed: "hidden"
            -->
            <svg
              v-if="menuDropdown"
              class="h-6 w-6"
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 24 24"
              stroke="currentColor"
              aria-hidden="true"
            >
              <path
                stroke-linecap="round"
                stroke-linejoin="round"
                stroke-width="2"
                d="M6 18L18 6M6 6l12 12"
              />
            </svg>
          </button>
        </div>
        <!-- Tablet+ menu  -->
        <div
          class="flex-1 flex items-center justify-center sm:items-stretch sm:justify-start"
        >
          <div class="flex-shrink-0 flex items-center">
            <p class="hidden sm:block text-lg font-bold text-white">
              Guild Commerce
            </p>
          </div>
          <div class="hidden sm:block sm:ml-6">
            <div class="flex space-x-4">
              <router-link
                to="/"
                class="px-3 py-2 rounded-md font-medium text-gray-300 hover:text-white hover:bg-gray-700"
                :class="{
                  'text-yellow-300': $route.name == 'HomePage',
                }"
                >Home</router-link
              >
              <router-link
                to="/mu-servers"
                class="px-3 py-2 rounded-md font-medium text-gray-300 hover:text-white hover:bg-gray-700"
                :class="{
                  'text-yellow-300': $route.name == 'MuServersPage',
                }"
                >MU Servers</router-link
              >
              <router-link
                to="/user/posts"
                class="px-3 py-2 rounded-md font-medium text-gray-300 hover:text-white hover:bg-gray-700"
                :class="{
                  'text-yellow-300': $route.name == 'UserPostsPage',
                }"
                >My Posts</router-link
              >
            </div>
          </div>
        </div>
        <!-- Sign in button -->
        <div v-if="!isLoggedIn">
          <router-link to="/sign-in">
            <button
              class="px-4 py-2 rounded-md hover:bg-gray-700 border border-white focus:outline-none"
              :class="{ 'border-yellow-300': $route.name == 'SignInPage' }"
            >
              <span class="uppercase text-sm font-medium text-white">
                Sign In
              </span>
            </button>
          </router-link>
        </div>

        <div
          v-if="isLoggedIn"
          class="absolute inset-y-0 right-0 flex items-center pr-2 sm:static sm:inset-auto sm:ml-6 sm:pr-0"
        >
          <!-- User dropdown -->
          <div class="ml-3 relative">
            <div class="rounded-md overflow-hidden">
              <button
                @click="dropdown = !dropdown"
                class="px-4 py-2 hover:bg-gray-70 focus:outline-none"
                :class="{ 'bg-yellow-300 ': dropdown }"
                id="user-menu"
                aria-haspopup="true"
              >
                <span class="sr-only">Open user menu</span>
                <div class="flex items-center text-md">
                  <div class="h-8 w-8 rounded-full mr-2 overflow-hidden">
                    <img
                      class="h-full w-full"
                      src="https://cdn.pixabay.com/photo/2020/11/10/01/34/pet-5728249_960_720.jpg"
                      alt=""
                    />
                  </div>
                  <div
                    class="flex text-white"
                    :class="{ 'text-gray-900': dropdown }"
                  >
                    {{ nickname }}
                    <svg
                      class="-mr-1 ml-2 h-5 w-5"
                      xmlns="http://www.w3.org/2000/svg"
                      viewBox="0 0 20 20"
                      fill="currentColor"
                      aria-hidden="true"
                    >
                      <path
                        fill-rule="evenodd"
                        d="M5.293 7.293a1 1 0 011.414 0L10 10.586l3.293-3.293a1 1 0 111.414 1.414l-4 4a1 1 0 01-1.414 0l-4-4a1 1 0 010-1.414z"
                        clip-rule="evenodd"
                      />
                    </svg>
                  </div>
                </div>
              </button>
            </div>
            <!-- User dropdown panel -->
            <div
              v-if="dropdown"
              class="origin-top-right absolute right-0 mt-2 w-40 rounded-md shadow-lg py-1 bg-white ring-1 ring-black ring-opacity-5"
              role="menu"
              aria-orientation="vertical"
              aria-labelledby="user-menu"
            >
              <router-link
                to="/user/profile"
                @click.native="dropdown = !dropdown"
                class="block px-4 py-2 text-gray-700 hover:text-white hover:bg-gray-900 cursor-pointer"
                role="menuitem"
                >My Profile</router-link
              >
              <a
                @click="
                  signOut();
                  dropdown = !dropdown;
                "
                class="block px-4 py-2 text-gray-700 hover:text-white hover:bg-gray-900 cursor-pointer"
                role="menuitem"
                >Sign out</a
              >
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Mobile menu  -->
    <div v-if="menuDropdown" class="border-b border-gray-800 sm:hidden">
      <div class="px-2 pb-3 pt-2">
        <router-link
          to="/"
          class="block px-3 py-2 rounded-md font-medium text-gray-300 hover:text-white hover:bg-gray-700"
          :class="{ 'text-gray-900 bg-yellow-300': $route.name == 'HomePage' }"
          >Home</router-link
        >
      </div>
      <div class="px-2 pb-3">
        <router-link
          to="/mu-servers"
          class="block px-3 py-2 rounded-md font-medium text-gray-300 hover:text-white hover:bg-gray-700"
          :class="{
            'text-gray-900 bg-yellow-300': $route.name == 'MuServersPage',
          }"
          >MU Servers</router-link
        >
      </div>
      <div class="px-2 pb-3">
        <router-link
          to="/user/posts"
          class="block px-3 py-2 rounded-md font-medium text-gray-300 hover:text-white hover:bg-gray-700"
          :class="{
            'text-gray-900 bg-yellow-300': $route.name == 'UserPostsPage',
          }"
          >My Posts</router-link
        >
      </div>
    </div>
  </nav>
</template>

<script>
export default {
  data() {
    return {
      dropdown: false,
      menuDropdown: false,
      nickname: "Charspunk",
      isLoggedIn: false,
    };
  },
  methods: {
    getUsername() {
      return this.username;
    },
    getNickname() {
      return this.nickname;
    },
    confirmToken() {
      this.isLoggedIn = true;
    },
    signOut() {},
  },
  mounted() {
    this.confirmToken();
  },
};
</script>
