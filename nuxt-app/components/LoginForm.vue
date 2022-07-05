<template>
  <div>
    <Heading>Login</Heading>
    <Alert type="danger" warning-icon v-if="!successful">
      Wrong username or password
    </Alert>
    <div v-if="!loggedInUsername && !loggedInPassword && !loggedInRole">
      <div class="form-outline mt-4 mb-2">
        <label class="form-label" for="username"> Username </label>
        <input
          type="text"
          id="username"
          class="form-control"
          ref="usernameInput"
          required
          autofocus
        />
      </div>

      <!-- Password input -->
      <div class="form-outline mb-2">
        <label class="form-label" for="password">Password</label>
        <input
          type="password"
          id="password"
          class="form-control"
          ref="passwordInput"
          required
        />
      </div>

      <!-- Submit button set type=submit -->
      <button @click="login" class="btn btn-primary btn-block mb-4 mt-2 w-100">
        Login
      </button>

      <!-- Register buttons -->
      <div class="text-center">
        <p>To create an account for you contact an administrator or teacher.</p>
      </div>
    </div>
    <div v-else>
      <Alert class="d-flex align-items-center justify-content-between"
        >You are already logged in!
        <button
          @click="logout"
          class="btn btn-danger ms-3 float-end center align-self-center"
        >
          Logout?
        </button>
      </Alert>
    </div>
  </div>
</template>

<script lang="ts">
import axios from "axios";

export default {
  data() {
    return {
      successful: true,
    };
  },
  computed: {
    loggedInUsername() {
      return useCookie("username").value;
    },
    loggedInPassword() {
      return useCookie("password").value;
    },
    loggedInRole() {
      return useCookie("role").value;
    },
  },

  methods: {
    setUsername(username: string) {
      useCookie("username").value = username;
      useUsername().value = username;
    },
    setPassword(password: string) {
      useCookie("password").value = password;
      usePassword().value = password;
    },
    setRole(role: string) {
      useCookie("role").value = role;
      useRole().value = role;
    },
    logout() {
      useCookie("username").value = undefined;
      useCookie("password").value = undefined;
      useCookie("role").value = undefined;
    },
    async login() {
      const username = this.$refs.usernameInput.value;
      const password = this.$refs.passwordInput.value;

      const { data: valid } = await axios.get(
        `http://localhost:8080/api/users/login/${username}/${password}`
      );

      if (valid) {
        console.log("correct");
        // store users role from backend
        const { data: role } = await axios.get(
          `http://localhost:8080/api/users/${username}/role`,
          {
            headers: {
              Authorization: `Basic ${btoa(`${username}:${password}`)}`,
            },
          }
        );

        this.setUsername(username);
        this.setPassword(password);
        this.setRole(role)

        this.$router.push("/");
      } else {
        this.successful = false;
      }
    },
  },
};
</script>
