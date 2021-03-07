<template>
  <div v-if="data != []">
    <div class="grid grid-cols-12 gap-6">
      <div
        v-for="post in data"
        :key="post.id"
        class="col-span-12 sm:col-span-6 md:col-span-4 lg:col-span-3"
      >
        <div class="py-4 px-6 border hover:border hover:border-gray-900">
          <!-- Item name, item level and item options -->
          <div class="">
            <div class="text-gray-900 font-bold">
              {{ post.muItem.name }} <span> +{{ post.itemLevel }}</span>
            </div>
            <div class="text-gray-500">{{ post.muItemOption.name }}</div>
          </div>
          <!-- divider -->
          <hr v-if="dropdowns.showSeeMore"  class="my-3" />
          <div v-if="dropdowns.showSeeMore" class="">
            <div class="text-gray-900">Contact: {{post.contact}}</div>
            <div class="text-gray-900">Observation: {{post.observation}}</div>
          </div>
          <!-- divider -->
          <hr class="my-3" />
          <!-- user and createdDate -->
          <footer class="flex sm:flex-col justify-between text-sm">
            <div class="text-gray-900">By {{ post.user.nickname }}</div>
            <div class="text-gray-500">Hace {{ post.createdDate }}</div>
          </footer>
        </div>
        <div @click="dropdowns.showSeeMore = !dropdowns.showSeeMore" class="py-1 bg-gray-900 hover:bg-black">
          <div v-if="dropdowns.showSeeMore" class="cursor-pointer text-xs uppercase text-center text-gray-200">ocult </div>
          <div v-else class="cursor-pointer text-xs uppercase text-center text-gray-200">see more</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      dropdowns: {
        showSeeMore: false,
      }
    }
  },
  props: {
    data: {
      type: Array,
      default: [],
    },
  },
};
</script>
