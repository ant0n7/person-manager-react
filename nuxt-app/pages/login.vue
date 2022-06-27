<!-- <script setup>
const loggedInUsername = useUsername();
</script> -->

<template>
  <div class="container">
    <div class="row">
      <div class="col-md-4"></div>
      <div class="col-md-4 col-12">
        <Heading>Login</Heading>
        <!-- <form action="/"> -->
        <!-- <form> -->
          <!-- Email input -->
          <div class="form-outline mt-4 mb-2">
            <input
              type="text"
              id="username"
              class="form-control"
              ref="usernameInput"
              required
              autofocus
            />
            <label class="form-label" for="username">Username</label>
          </div>

          <!-- Password input -->
          <div class="form-outline mb-2">
            <input
              type="password"
              id="password"
              class="form-control"
              ref="passwordInput"
              required
            />
            <label class="form-label" for="password">Password</label>
          </div>

          <!-- Submit button set type=submit -->
          <button @click="login" class="btn btn-primary btn-block mb-4">
            Sign in
          </button>

          <!-- Register buttons -->
          <div class="text-center">
            <p>Not a member? <a href="#!">Register</a></p>
          </div>

          <div>
            <p>Store user: {{ loggedInUsername }}</p>
          </div>

          <button @click="test" class="btn btn-primary btn-block mb-4">
            test data
          </button>
        <!-- </form> -->
      </div>
    </div>
  </div>
</template>

<script>
export default {
  computed: {
    storageAttr() {
      // const { $storage } = useNuxtApp();
      // return $storage;
    },
    loggedInUsername() {
      return $storage().getItem('username');
    },
  },

  methods: {
    setUsername(username) {
      $storage().setItem('username', username);
    },
    login() {
      const username = this.$refs.usernameInput.value;
      const password = this.$refs.passwordInput.value;

      console.log("username: " + username);
      console.log(this.loggedInUsername);

      this.setUsername(username);

      console.log(this.loggedInUsername);

      // check if user is in backend  
      // store users role from backend

    },
  },
};
</script>
