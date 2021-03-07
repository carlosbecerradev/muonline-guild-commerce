<template>
  <page-layout>
    <div class="max-w-lg w-full p-4 bg-white md:px-8 rounded-md shadow">
      <div>
        <h2 class="py-4 text-center text-4xl font-extrabold text-gray-900">
          Sign In
        </h2>
      </div>
      <span class="sr-only">post form</span>
      <form @submit.prevent="signIn(userAccount)">
        <div class="overflow-hidden sm:rounded-md">
          <div class="py-4">
            <div class="grid grid-cols-6 gap-6">
              <div class="col-span-6">
                <form-label :text="'Email'"></form-label>
                <input
                  v-model="userAccount.email"
                  type="email"
                  class="mt-1 block w-full py-1 px-3 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 text-sm"
                />
              </div>
              <div class="col-span-6">
                <form-label :text="'Password'"></form-label>
                <input
                  v-model="userAccount.password"
                  type="password"
                  class="mt-1 block w-full py-1 px-3 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 text-sm"
                />
              </div>

              <div class="col-span-6 sm:col-span-3">
                <primary-button :text="'Confirm'"></primary-button>
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
import { mapActions } from "vuex";
import PageLayout from "@/layouts/PageLayout.vue";
import PrimaryButton from "@/components/button/PrimaryButton.vue";
import SecondaryButton from "@/components/button/SecondaryButton.vue";
import FormLabel from "@/components/form/FormLabel.vue";

export default {
  data() {
    return {
      userAccount: {
        email: "",
        password: "",
      },
    };
  },
  methods: {
    ...mapActions(["loggedInSuccessfully"]),
    async signIn(userAccount) {
      try {
        const response = await fetch(`http://localhost:8088/api/auth/login`, {
          method: "POST",
          headers: {
            "content-type": "application/json",
          },
          body: JSON.stringify(userAccount),
        });
        console.log(response);
        if (response.status == 200) {
          const body = await response.json();
          this.loggedInSuccessfully(body);
          console.log(body);
        }
      } catch (error) {
        console.error(error);
      }
    },
  },
  components: {
    PageLayout,
    PrimaryButton,
    SecondaryButton,
    FormLabel,
  },
};
</script>
