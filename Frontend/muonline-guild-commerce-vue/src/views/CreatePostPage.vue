<template>
  <page-layout>
    <div class="max-w-lg w-full p-4 bg-white md:px-8 rounded-md shadow">
      <div>
        <h2 class="py-4 text-center text-4xl font-extrabold text-gray-900">
          New Post
        </h2>
      </div>
      <span class="sr-only">post form</span>
      <form @submit.prevent="eSUbmit">
        <div class="overflow-hidden sm:rounded-md">
          <div class="py-4">
            <div class="grid grid-cols-6 gap-6">
              <div class="col-span-6 sm:col-span-3">
                <form-label :text="'Post Type'"></form-label>
                <select
                  class="mt-1 block w-full py-1 px-3 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 text-sm"
                >
                  <option>Sell</option>
                </select>
              </div>
              <div class="col-span-6 sm:col-span-3">
                <form-label :text="'Mu Server'"></form-label>
                <select
                  class="mt-1 block w-full py-1 px-3 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 text-sm"
                >
                  <option>Mu X</option>
                </select>
              </div>
              <div class="col-span-6">
                <form-label :text="'Item Name'"></form-label>
                <input
                  type="text"
                  class="mt-1 block w-full py-1 px-3 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 text-sm"
                />
              </div>

              <div class="col-span-6 sm:col-span-2">
                <form-label :text="'Item Level'"></form-label>
                <input
                  type="number"
                  placeholder="0"
                  min="0"
                  value="0"
                  class="mt-1 block w-full py-1 px-3 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 text-sm"
                />
              </div>

              <div class="col-span-6 sm:col-span-4">
                <form-label :text="'Item Options'"></form-label>
                <select
                  class="mt-1 block w-full py-1 px-3 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
                >
                  <option>FO</option>
                </select>
              </div>

              <div class="col-span-6 sm:col-span">
                <form-label :text="'Observation'"></form-label>
                <textarea
                  type="text"
                  placeholder="I need it"
                  class="mt-1 block w-full py-1 px-3 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
                >
                </textarea>
              </div>

              <div class="col-span-6 sm:col-span-3">
                <primary-button :text="'Save'"></primary-button>
              </div>

              <div class="col-span-6 sm:col-span-3">
                <secondary-button :text="'Cancel'"></secondary-button>
              </div>
            </div>
          </div>
        </div>
      </form>
    </div>
  </page-layout>
</template>

<script>
import PageLayout from "@/layouts/PageLayout.vue";
import PrimaryButton from "@/components/button/PrimaryButton.vue";
import SecondaryButton from "@/components/button/SecondaryButton.vue";
import FormLabel from "@/components/form/FormLabel.vue";

export default {
  components: { PageLayout, PrimaryButton, SecondaryButton, FormLabel },
  methods: {
    eSUbmit() {
      console.log("submit click");
    },
  },
};
</script>
