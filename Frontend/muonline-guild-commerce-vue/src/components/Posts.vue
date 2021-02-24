<template>
  <div v-if="data != []">
    <div class="grid grid-cols-12 gap-6">
      <div
        v-for="i in data"
        :key="i"
        class="col-span-12 sm:col-span-6 md:col-span-4 lg:col-span-3 bg-gray-100"
      >
        <div class="py-4 px-6 border hover:border hover:border-gray-900">
          <!-- Item name, item level and item options -->
          <div class="">
            <div class="text-gray-900 font-bold">
              Black Dragon Armor <span> +13</span>
            </div>
            <div class="text-gray-500">Full Options</div>
          </div>
          <!-- See more button -->
          <div class="mt-4 cursor-pointer hover:bg-gray-900 hover:text-white">
            <div class="block">
              <div class="flex justify-center items-center text-xs">
                <span class="block py-1">see more</span>
                <svg
                  class="ml-2 mt-1 h-3 w-3"
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
            <div class="hidden">
              <div class="flex justify-center items-center text-xs">
                <span class="block py-1">close</span>
                <svg
                  class="ml-2 mt-1 h-3 w-3"
                  xmlns="http://www.w3.org/2000/svg"
                  fill="none"
                  viewBox="0 0 24 24"
                  stroke="currentColor"
                >
                  <path
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    stroke-width="2"
                    d="M6 18L18 6M6 6l12 12"
                  />
                </svg>
              </div>
            </div>
          </div>
          <!-- See more panel -->
          <div class="hidden">
            <div class="">
              <div class="capitalize">
                <span class="text-xs">contact</span>
              </div>
              <div class="text-gray-900">+999000111</div>
            </div>
            <!-- divider -->
            <hr class="my-3" />
            <div class="">
              <div class="capitalize">
                <span class="text-xs">Observation</span>
              </div>
              <p class="m-0 items-baseline">I really need it.</p>
            </div>
          </div>
          <!-- divider -->
          <hr class="my-3" />
          <!-- user and createdDate -->
          <footer class="flex justify-between text-sm">
            <div class="text-gray-900">By Charspunk</div>
            <div class="text-gray-500">Hace 1h</div>
          </footer>
        </div>
      </div>
    </div>
  </div>
  <!--
<div class="overflow-hidden">
      <table class="min-w-full divide-y divide-gray-200 border">
        <thead>
          <tr>
            <th
              scope="col"
              class="px-6 py-3 bg-gray-50 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"
            >
              Post Type
            </th>
            <th
              scope="col"
              class="px-6 py-3 bg-gray-50 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"
            >
              Item
            </th>
            <th
              scope="col"
              class="px-6 py-3 bg-gray-50 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"
            >
              By
            </th>
            <th
              scope="col"
              class="px-6 py-3 bg-gray-50 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"
            >
              Contact
            </th>
            <th
              scope="col"
              class="px-6 py-3 bg-gray-50 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"
            >
              Observation
            </th>
          </tr>
        </thead>

        <tbody class="bg-white divide-y divide-gray-200">
          <tr v-for="i in 6" :key="i">
            <td class="px-6 py-4 whitespace-nowrap">
              <div class="text-gray-900">Sell</div>
            </td>
            <td class="px-6 py-4 whitespace-nowrap">
              <div class="flex items-center">
                <div class="">
                  <div class="font-medium text-gray-900">
                    Black Dragon Armor
                    <span> +13</span>
                  </div>
                  <div class="text-gray-500">Full Options</div>
                </div>
              </div>
            </td>
            <td class="px-6 py-4 whitespace-nowrap">
              <div class="text-gray-900">Charspunk</div>
              <div class="text-gray-500">Hace 1h</div>
            </td>
            <td class="px-6 py-4 whitespace-nowrap">
              <div class="text-gray-900">+999000111</div>
            </td>
            <td class="px-6 py-4 text-gray-900">
              <p class="m-0 overflow-auto max-h-20 md:max-h-full flex items-baseline">
                Para el CS que se viene Lorem ipsum dolor sit amet consectetur adipisicing elit. Tempora quidem illo facere delectus, voluptatum sapiente assumenda laboriosam ducimus quasi mollitia quo natus nam deserunt omnis amet incidunt ipsam officia nihil!
              </p>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

  -->
</template>

<script>
export default {
  props: {
    data: {
      type: Array,
      default: [],
    },
  },
};
</script>
