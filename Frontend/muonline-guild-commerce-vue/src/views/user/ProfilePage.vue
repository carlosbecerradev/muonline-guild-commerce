<template>
  <default-layout>
    <!-- Mu Server -->
    <section v-if="true" class="bg-gray-900">
      <div class="max-w-7xl mx-auto px-2 sm:px-6 lg:px-8 py-8 lg:py-12">
        <!-- This example requires Tailwind CSS v2.0+ -->
        <div class="sm:flex md:items-center md:justify-between">
          <div class="flex-1">
            <h2 class="font-extrabold text-yellow-300 text-4xl">
              <span class="capitalize">{{ nickname }}</span> Account
            </h2>
            <div class="mt-1 flex flex-wrap sm:flex-row">
              <button
                @click="showProfile"
                class="mt-2 mr-6 px-4 py-2 rounded border focus:outline-none"
                :class="{ 'border-yellow-300': sections.profile.show }"
              >
                <div
                  class="flex items-center text-gray-200 hover:text-yellow-300"
                >
                  <svg
                    class="h-4 w-4 mr-1"
                    xmlns="http://www.w3.org/2000/svg"
                    fill="none"
                    viewBox="0 0 24 24"
                    stroke="currentColor"
                  >
                    <path
                      stroke-linecap="round"
                      stroke-linejoin="round"
                      stroke-width="2"
                      d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z"
                    />
                  </svg>
                  <span>Profile</span>
                </div>
              </button>
              <button
                @click="showChangeEmail"
                class="mt-2 mr-6 px-4 py-2 rounded border focus:outline-none"
                :class="{ 'border-yellow-300': sections.changeEmail.show }"
              >
                <div
                  class="flex items-center text-gray-200 hover:text-yellow-300"
                >
                  <svg
                    class="h-4 w-4 mr-1"
                    xmlns="http://www.w3.org/2000/svg"
                    fill="none"
                    viewBox="0 0 24 24"
                    stroke="currentColor"
                  >
                    <path
                      stroke-linecap="round"
                      stroke-linejoin="round"
                      stroke-width="2"
                      d="M16 12a4 4 0 10-8 0 4 4 0 008 0zm0 0v1.5a2.5 2.5 0 005 0V12a9 9 0 10-9 9m4.5-1.206a8.959 8.959 0 01-4.5 1.207"
                    />
                  </svg>
                  <span>Change email</span>
                </div>
              </button>
              <button
                @click="showChangePassword"
                class="mt-2 mr-6 px-4 py-2 rounded border focus:outline-none"
                :class="{ 'border-yellow-300': sections.changePassword.show }"
              >
                <div
                  class="flex items-center text-gray-200 hover:text-yellow-300"
                >
                  <svg
                    class="h-4 w-4 mr-1"
                    xmlns="http://www.w3.org/2000/svg"
                    fill="none"
                    viewBox="0 0 24 24"
                    stroke="currentColor"
                  >
                    <path
                      stroke-linecap="round"
                      stroke-linejoin="round"
                      stroke-width="2"
                      d="M15 7a2 2 0 012 2m4 0a6 6 0 01-7.743 5.743L11 17H9v2H7v2H4a1 1 0 01-1-1v-2.586a1 1 0 01.293-.707l5.964-5.964A6 6 0 1121 9z"
                    />
                  </svg>
                  <span>Update password</span>
                </div>
              </button>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- Profile Information -->
    <section v-if="sections.profile.show">
      <div class="max-w-7xl mx-auto px-2 sm:px-6 lg:px-8 py-6">
        <h3 class="text-lg font-bold text-gray-900">Profile Information</h3>

        <div class="py-4">
          <form>
            <div class="grid grid-cols-12 gap-6">
              <div class="col-span-12 sm:col-span-6 md:col-span-4">
                <form-label :text="'Email / Username'"></form-label>
                <input
                  value="somemeail.12.cool@some.com"
                  disabled
                  type="text"
                  class="mt-1 block w-full py-1 px-3 border border-gray-300 bg-white rounded-md shadow"
                />
              </div>
              <div class="col-span-12 sm:col-span-6 md:col-span-4">
                <form-label :text="'Nickname'"></form-label>
                <input
                  v-model="nickname"
                  type="text"
                  class="mt-1 block w-full py-1 px-3 border border-gray-300 bg-white rounded-md shadow"
                />
              </div>
              <div class="col-span-12 sm:col-span-6 md:col-span-4">
                <form-label :text="'Create Date'"></form-label>
                <input
                  value="Saturday, Jul 25, 2020 09:24:10 AM"
                  disabled
                  type="text"
                  class="mt-1 block w-full py-1 px-3 border border-gray-300 bg-white rounded-md shadow"
                />
              </div>

              <div class="col-span-12 sm:col-span-6">
                <form-label :text="'Contact'"></form-label>
                <textarea
                  type="text"
                  placeholder="I need it"
                  class="mt-1 block w-full py-1 px-3 border border-gray-300 bg-white rounded-md shadow"
                >
                </textarea>
              </div>
              <div class="col-span-12 sm:col-start-1 sm:col-end-4">
                <primary-button :text="'Save Changes'" />
              </div>
            </div>
          </form>
        </div>
      </div>
    </section>

    <!-- Change Email -->
    <section v-if="sections.changeEmail.show">
      <div class="max-w-7xl mx-auto px-2 sm:px-6 lg:px-8 py-6">
        <h3 class="text-lg font-bold text-gray-900">Change Email</h3>
        <div class="py-4">
          Email change feature is disabled for security reasons.
        </div>
      </div>
    </section>

    <!-- Update password -->
    <section v-if="sections.changePassword.show">
      <div class="max-w-7xl mx-auto px-2 sm:px-6 lg:px-8 py-6">
        <h3 class="text-lg font-bold text-gray-900">Update password</h3>

        <div class="py-4">
          <form>
            <div class="grid grid-cols-12 gap-4">
              <!-- Secutity Code -->
              <div class="col-span-9 sm:col-span-6">
                <form-label :text="'Security Code'"></form-label>
                <input
                  disabled
                  type="text"
                  placeholder="Enter the security code you will receive on your current email"
                  class="mt-1 block w-full py-1 px-3 border border-gray-300 bg-white rounded-md shadow"
                />
              </div>
              <!-- Request secutity Code -->
              <div class="col-span-3 sm:col-span-2">
                <div
                  class="mt-7 cursor-pointer shadow rounded bg-gray-900 hover:bg-black"
                >
                  <div
                    class="sm:py-2 flex flex-col sm:flex-row justify-center items-center w-full"
                  >
                    <div class="text-white text-xs">Request</div>
                    <div class="text-white text-xs sm:ml-1">Code</div>
                  </div>
                </div>
              </div>
              <!-- divider -->
              <div class="col-span-12">
                <hr class="my-3" />
              </div>
              <!-- New Password -->
              <div class="col-span-12 sm:col-span-6">
                <form-label :text="'New Password'"></form-label>
                <input
                  type="text"
                  placeholder="Enter your password"
                  class="mt-1 block w-full py-1 px-3 border border-gray-300 bg-white rounded-md shadow"
                />
              </div>
              <!-- Confirm Password -->
              <div class="col-span-12 sm:col-span-6">
                <form-label :text="'Confirm Password'"></form-label>
                <input
                  type="text"
                  placeholder="Confirm your password again"
                  class="mt-1 block w-full py-1 px-3 border border-gray-300 bg-white rounded-md shadow"
                />
              </div>
              <!-- Submit Button -->
              <div class="col-span-12 sm:col-span-6">
                <primary-button :text="'Change Password'"></primary-button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </section>
  </default-layout>
</template>

<script>
import DefaultLayout from "@/layouts/DefaultLayout.vue";
import PrimaryButton from "@/components/button/PrimaryButton.vue";
import SecondaryButton from "@/components/button/SecondaryButton.vue";
import FormLabel from "@/components/form/FormLabel.vue";

export default {
  data() {
    return {
      nickname: "Charspunk",
      sections: {
        profile: {
          show: true,
        },
        changeEmail: {
          show: false,
        },
        changePassword: {
          show: false,
        },
      },
    };
  },
  methods: {
    showProfile() {
      this.sections.profile.show = true;
      this.sections.changeEmail.show = false;
      this.sections.changePassword.show = false;
    },
    showChangeEmail() {
      this.sections.profile.show = false;
      this.sections.changeEmail.show = true;
      this.sections.changePassword.show = false;
    },
    showChangePassword() {
      this.sections.profile.show = false;
      this.sections.changeEmail.show = false;
      this.sections.changePassword.show = true;
    },
  },
  components: { DefaultLayout, PrimaryButton, SecondaryButton, FormLabel },
};
</script>
