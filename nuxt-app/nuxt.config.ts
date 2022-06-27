import { defineNuxtConfig } from 'nuxt'

// https://v3.nuxtjs.org/api/configuration/nuxt.config
export default defineNuxtConfig({
  app: {
    head: {
      title: 'Student Manager',
      meta: [
        { charset: 'utf-8' },
        { name: 'viewport', content: 'width=device-width, initial-scale=1' },
        { hid: 'description', name: 'description', content: 'Student Manager to manage students and their modules' }
      ],
      link: [
        { rel: 'icon', type: 'image/x-icon', href: '/favicon.ico' }
      ],
      script: [
        { 
          src: 'https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js',
          body: true 
        },
      ]
    }
  },
  css: [
    'bootstrap/dist/css/bootstrap.min.css',
    '@/assets/css/main.scss',
  ],
  // modules: [
  //   '@nuxtjs/axios',
  //   '@nuxtjs/auth-next',
  // ]
})
