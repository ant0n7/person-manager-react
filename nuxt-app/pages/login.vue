<!-- <script setup>
const loggedInUsername = useUsername();
</script> -->

<template>
  <div class="container">
    <div class="row">
      <div class="col-md-4"></div>
      <div class="col-md-4 col-12">
        <Heading>Login</Heading>

        <!-- <Alert v-if="userValid !== undefined ? userValid : false">Wrong username or password.</Alert> -->
        <Alert v-if="false">Wrong username or password.</Alert>
        <form action="/">
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
        </form>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import axios from "axios";

export default {
  computed: {
    loggedInUsername() {
      return useCookie("username").value;
    },
    loggedInPassword() {
      return useCookie("password").value;
    },
  },

  methods: {
    setUsername(username: string) {
      useCookie("username").value = username;
    },
    setPassword(password: string) {
      useCookie("password").value = password;
    },
    async login() {
      const username = this.$refs.usernameInput.value;
      const password = this.$refs.passwordInput.value;

      this.setUsername(username);
      this.setPassword(password);

      const { data: valid } = await axios.get(
        `http://localhost:8080/api/users/login/${username}/${password}`
      );

      if (valid) {
        console.log("correct");
        // store users role from backend
        // const { data: role } = await axios.get(
        //   `http://localhost:8080/api/users/${username}/role`,
        //   {
        //     headers: {
        //       Authorization: `Basic ${btoa(`${username}:${password}`)}`,
        //     },
        //   }
        // );

        // useCookie("role").value = role;
        useCookie("role").value = "TEACHER";
      } else {
        alert("Wrong username or password");
      }
    },
  },
};
</script>
