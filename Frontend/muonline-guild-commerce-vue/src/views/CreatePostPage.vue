<template>
  <page-layout>
    <div class="max-w-lg w-full p-4 bg-white md:px-8 rounded-md shadow">
      <div>
        <h2 class="py-4 text-center text-4xl font-extrabold text-gray-900">
          New Post
        </h2>
      </div>
      <span class="sr-only">post form</span>
      <form
        @submit.prevent="createPost"
        v-if="muServers != null && postTypes != null && muItemOptions != null"
      >
        <div class="overflow-hidden sm:rounded-md">
          <div class="py-4">
            <div class="grid grid-cols-6 gap-6">
              <div class="col-span-6 sm:col-span-3">
                <form-label :text="'Post Type'"></form-label>
                <select
                  v-model="post.postTypeId"
                  class="mt-1 block w-full py-1 px-3 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-gray-900 text-sm"
                >
                  <option
                    v-for="postType in postTypes"
                    :key="postType.id"
                    :value="postType.id"
                  >
                    {{ postType.name }}
                  </option>
                </select>
              </div>
              <div class="col-span-6 sm:col-span-3">
                <form-label :text="'Mu Server'"></form-label>
                <select
                  v-model="post.muServerId"
                  class="mt-1 block w-full py-1 px-3 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-gray-900 text-sm"
                >
                  <option
                    v-for="muServer in muServers"
                    :key="muServer.id"
                    :value="muServer.id"
                  >
                    {{ muServer.name }}
                  </option>
                </select>
              </div>
              <div class="col-span-6">
                <form-label :text="'Item Name'"></form-label>
                <autocomplete-mu-items
                  :placeholder="'Whrite to search some item'"
                  :items="muItems"
                  @inputText="fetchMuItemsByName"
                  @itemSelected="post.muItemId = $event.id"
                />
              </div>

              <div class="col-span-6 sm:col-span-2">
                <form-label :text="'Item Level'"></form-label>
                <input
                  v-model="post.itemLevel"
                  type="number"
                  placeholder="0"
                  min="0"
                  value="0"
                  class="mt-1 block w-full py-1 px-3 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-gray-900 text-sm"
                />
              </div>

              <div class="col-span-6 sm:col-span-4">
                <form-label :text="'Item Options'"></form-label>
                <select
                  v-model="post.muItemOptionId"
                  class="mt-1 block w-full py-1 px-3 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-gray-900"
                >
                  <option
                    v-for="muItemOption in muItemOptions"
                    :key="muItemOption.id"
                    :value="muItemOption.id"
                  >
                    {{ muItemOption.name }}
                  </option>
                </select>
              </div>

              <div class="col-span-6 sm:col-span">
                <form-label :text="'Observation'"></form-label>
                <textarea
                  v-model="post.observation"
                  type="text"
                  placeholder="I need it"
                  class="mt-1 block w-full py-1 px-3 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-gray-900"
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
import { mapGetters } from "vuex";
import PageLayout from "@/layouts/PageLayout.vue";
import PrimaryButton from "@/components/button/PrimaryButton.vue";
import SecondaryButton from "@/components/button/SecondaryButton.vue";
import FormLabel from "@/components/form/FormLabel.vue";
import AutocompleteMuItems from "@/components/form/AutocompleteMuItems.vue";

export default {
  data() {
    return {
      post: {
        muServerId: 0,
        muItemId: 0,
        muItemOptionId: 0,
        postTypeId: 0,
        itemLevel: 0,
        observation: "",
      },
      muServers: null,
      muItems: null,
      muItemOptions: null,
      postTypes: null,
    };
  },
  mounted() {
    this.init();
  },
  updated() {
    console.log("post", this.post);
  },
  computed: {
    ...mapGetters(["accessToken"]),
  },
  methods: {
    async createPost() {
      try {
        this.post.itemLevel = parseInt(this.post.itemLevel);
        const response = await fetch(`http://localhost:8088/api/posts`, {
          method: "POST",
          headers: {
            "content-type": "application/json",
            Authorization: this.accessToken,
          },
          body: JSON.stringify(this.post),
        });
        console.log("post", this.post);
        console.log(response);
        if (response.status == 201) {
          const body = await response.json();
          console.log(body);
          this.$route.push("/user/posts")
        }
      } catch (error) {
        console.error(error);
      }
      console.log("submit click");
    },
    async fetchMuServers() {
      this.muServers = await this.fetchResource(
        "http://localhost:8088/api/mu-servers/list"
      );
      await this.getMuServerIdParam();
    },
    async fetchMuItemOptions() {
      this.muItemOptions = await this.fetchResource(
        "http://localhost:8088/api/mu-item-options/list"
      );
    },
    async fetcPostTypes() {
      this.postTypes = await this.fetchResource(
        "http://localhost:8088/api/post-types/list"
      );
    },
    async fetchMuItemsByName(name) {
      this.muItems = await this.fetchResource(
        `http://localhost:8088/api/mu-items/contain?name=${name}`
      );
    },
    async fetchResource(url) {
      try {
        const response = await fetch(url, {
          method: "GET",
          headers: {
            Authorization: this.accessToken,
          },
        });
        console.log(response);
        if (response.status == 200) {
          const body = await response.json();
          console.log(body);
          return body;
        }
      } catch (error) {
        console.error(error);
      }
    },
    getMuServerIdParam() {
      this.post.muServerId =
        this.$route.query.muServerId > 0
          ? parseInt(this.$route.query.muServerId)
          : 0;
      console.log("query", this.$route.query.muServerId);
    },
    init() {
      this.fetchMuServers();
      this.fetchMuItemOptions();
      this.fetcPostTypes();
      this.fetchMuItemOptions();
    },
  },
  components: {
    PageLayout,
    PrimaryButton,
    SecondaryButton,
    FormLabel,
    AutocompleteMuItems,
  },
};
</script>
