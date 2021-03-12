<template>
  <div v-if="data != []">
    <div class="grid grid-cols-12 gap-6">
      <div
        v-for="post in data"
        :key="post.id"
        class="col-span-12 sm:col-span-6 lg:col-span-4"
      >
        <div class="relative">
          <div
            class="py-4 px-6 border shadow-lg hover:border hover:border-gray-900"
          >
            <!-- Item name, item level and item options -->
            <div class="mb-3">
              <div class="text-gray-800">{{ post.postType.name }}</div>
              <div class="text-gray-900 font-bold">
                {{ post.muItem.name }} <span> +{{ post.itemLevel }}</span>
              </div>
              <div class="text-gray-800">{{ post.muItemOption.name }}</div>
              <div class="text-gray-800">{{ post.muServer.name }}</div>
            </div>
            <div class="mb-3 text-sm">
              <div v-if="post.contact" class="text-gray-600">
                Contact: {{ post.contact }}
              </div>
              <div v-if="post.observation" class="text-gray-600">
                Observation: {{ post.observation }}
              </div>
            </div>
            <!-- user and createdDate -->
            <footer class="flex justify-between text-sm">
              <div class="text-gray-600">By {{ post.user.nickname }}</div>
              <div class="text-gray-600">{{ post.createdDate }}</div>
            </footer>
          </div>
          <div v-if="post.enabled" class="absolute top-0 right-0">
            <div class="mt-4 mr-4">
              <button @click="actionsDropdown(post.id)">
                <svg
                  class="h-5 w-5"
                  xmlns="http://www.w3.org/2000/svg"
                  fill="none"
                  viewBox="0 0 24 24"
                  stroke="currentColor"
                >
                  <path
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    stroke-width="2"
                    d="M5 12h.01M12 12h.01M19 12h.01M6 12a1 1 0 11-2 0 1 1 0 012 0zm7 0a1 1 0 11-2 0 1 1 0 012 0zm7 0a1 1 0 11-2 0 1 1 0 012 0z"
                  />
                </svg>
              </button>
            </div>

            <div
              v-if="dropdown.actions.id == post.id && dropdown.actions.status"
              class="mr-4 absolute w-auto top-full right-0 bg-white shadow-lg border"
            >
              <div class="flex flex-col">
                <button
                  @click="completePost(post.id)"
                  class="text-sm hover:bg-gray-900"
                >
                  <div class="text-gray-900 hover:text-white py-1 px-6">
                    Complete
                  </div>
                </button>
                <button
                  @click="editPost(post.id)"
                  class="text-sm hover:bg-gray-900"
                >
                  <div class="text-gray-900 hover:text-white py-1 px-6">
                    Edit
                  </div>
                </button>
                <button
                  @click="deletePost(post.id)"
                  class="text-sm hover:bg-gray-900"
                >
                  <div class="text-gray-900 hover:text-white py-1 px-6">
                    Delete
                  </div>
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      dropdown: {
        actions: {
          id: 0,
          status: false,
        },
      },
    };
  },
  props: {
    data: {
      type: Array,
      default: [],
    },
  },
  methods: {
    actionsDropdown(id) {
      this.dropdown.actions.id = id;
      this.dropdown.actions.status = !this.dropdown.actions.status;
      console.log(id);
    },
  },
};
</script>
