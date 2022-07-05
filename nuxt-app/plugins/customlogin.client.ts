export default defineNuxtPlugin(nuxtApp => {
  // create variable for local storage
  return {
    provide: {
      storage: () => window.localStorage,
    }
  }
})