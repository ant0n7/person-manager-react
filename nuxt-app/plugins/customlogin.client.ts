export default defineNuxtPlugin(nuxtApp => {
  // create variable for local storage
  // const storage = window.localStorage;
  return {
    provide: {
      storage: () => window.localStorage,
    }
  }
})