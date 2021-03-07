import Vue from 'vue'
import Vuex from 'vuex'
import router from '../router/index.js'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    base_url: "http://localhost:8088/api",
    logged_in_data: {
      nickname: "",
      status: false,
    },
    local_storage_access_token_name: "MuOnlineGuildCommerceToken",
    access_token: "",
  },
  mutations: {
    SET_ACCESS_TOKEN(state, accessToken) {
      state.access_token = `Bearer ${accessToken}`;
    },
    SET_LOGGED_IN_DATA(state, data) {
      state.logged_in_data.nickname = data.nickname;
      state.logged_in_data.status = !state.logged_in_data.status;
    },
  },
  actions: {
    loggedInSuccessfully({ commit, state }, data) {
      localStorage.setItem(
        state.local_storage_access_token_name,
        data.accessToken
      );
      commit("SET_LOGGED_IN_DATA", data);
      commit("SET_ACCESS_TOKEN", data.accessToken);
      router.push("/");
    },
    async loadAccessToken({ commit, state }) {
      const localStorageAccessToken = localStorage.getItem(state.local_storage_access_token_name);
      if (localStorageAccessToken != null) {
        try {
          const response = await fetch(`http://localhost:8088/api/auth/check-access-token`, {
            method: "GET",
            headers: {
              Authorization: `Bearer ${localStorageAccessToken}`,
            },
          });
          // console.log(response)
          if (response.status == 200) {
            const data = await response.json();
            commit("SET_LOGGED_IN_DATA", data);
            commit("SET_ACCESS_TOKEN", localStorageAccessToken);
            // console.log("data", data)
            router.push("/").catch(err => {
              // Ignore the vuex err regarding  navigating to the page they are already on.
              if (
                err.name !== "NavigationDuplicated" &&
                !err.message.includes("Avoided redundant navigation to current location")
              ) {
                // But print any other errors to the console
                console.error(err);
              }
            });
          }
        } catch (error) {
          console.error(error);
        }
      }
      // console.log("localStorageAccessToken", localStorageAccessToken)
    },
    signOut({state}) {
      localStorage.removeItem(state.local_storage_access_token_name);
      router.push("/").catch(err => {
        // Ignore the vuex err regarding  navigating to the page they are already on.
        if (
          err.name !== "NavigationDuplicated" &&
          !err.message.includes("Avoided redundant navigation to current location")
        ) {
          // But print any other errors to the console
          console.error(err);
        }
      });
    },
  },
  getters: {
    url: state => state.base_url,
    accessToken: state => state.access_token,
    loggedInData: state => state.logged_in_data,
  },
  modules: {}
})
