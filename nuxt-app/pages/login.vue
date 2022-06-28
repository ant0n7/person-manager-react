<script lang="ts" setup>
const counter = useState("counter", () => useCookie("username").value);
const changeCookie = (value: string) => {
  useCookie("username").value = value;
  counter.value = value;
};
</script>

<template>
  <div class="container">
    <div class="row">
      <div class="col-md-4"></div>
      <div class="col-md-4 col-12">
        <Heading>Login</Heading>

        <div>
          counter: {{ counter }}
          <button @click="counter = 'Plus'">+</button>
          <button @click="counter = 'Minnus'">-</button>
          <button @click="() => changeCookie('PlusPlus')">++</button>
          <button @click="() => changeCookie('MinnusMinus')">--</button>
        </div>

        <!-- <Alert v-if="userValid !== undefined ? userValid : false">Wrong username or password.</Alert> -->
        <Alert v-if="false">Wrong username or password.</Alert>
        <!-- <form action="/"> -->
        <!-- <form> -->
        <!-- Email input -->
        <div v-if="!loggedInUsername && !loggedInPassword && !loggedInRole">
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
          <!-- </form> -->
        </div>
        <div v-else>
          <Alert class="d-inline-flex align-items-center"
            >You are already logged in!
            <button
              @click="logout"
              class="btn btn-danger ms-3 float-end center align-self-center"
            >
              Logout?
            </button></Alert
          >
        </div>
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
    loggedInRole() {
      return useCookie("role").value;
    },
  },

  methods: {
    setUsername(username: string) {
      useCookie("username").value = username;
    },
    setPassword(password: string) {
      useCookie("password").value = password;
    },
    setRole(role: string) {
      useCookie("role").value = role;
    },
    logout() {
      useCookie("username").value = undefined;
      useCookie("password").value = undefined;
      useCookie("role").value = undefined;
    },
    async login() {
      const username = this.$refs.usernameInput.value;
      const password = this.$refs.passwordInput.value;

      this.setUsername(username);
      this.setPassword(password);
      // this.setRole("TEACHER");

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

        useCookie("role").value = role;
        // this.setRole("TEACHER");

        this.$router.push("/");
      } else {
        alert("Wrong username or password");
      }
    },
  },
};
</script>
