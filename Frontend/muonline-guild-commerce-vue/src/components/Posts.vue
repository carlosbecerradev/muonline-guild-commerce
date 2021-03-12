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
              <div class="text-gray-900 font-bold">
                {{ post.muItem.name }} <span> +{{ post.itemLevel }}</span>
              </div>
              <div class="text-gray-800">{{ post.muItemOption.name }}</div>
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
