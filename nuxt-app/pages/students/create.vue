<template>
  <div class="container">
    <Heading>New Student</Heading>
    <form
      class="mt-4"
      accept-charset="UTF-8"
      v-on:submit.prevent="onSubmit()"
      method="POST"
    >
      <div class="mb-3">
        <label for="firstname" class="form-label">First Name</label>
        <input
          type="text"
          class="form-control"
          id="firstname"
          v-model="firstname"
        />
      </div>
      <div class="mb-3">
        <label for="lastname" class="form-label">Last Name</label>
        <input
          type="text"
          class="form-control"
          id="lastname"
          v-model="lastname"
        />
      </div>
      <div class="mb-3">
        <label for="username" class="form-label">Username</label>
        <input
          type="text"
          class="form-control"
          id="username"
          v-model="username"
        />
      </div>
      <div class="mb-3">
        <label for="email" class="form-label">Email</label>
        <input type="email" class="form-control" id="email" v-model="email" />
      </div>
      <div class="mb-3">
        <label for="password" class="form-label">Password</label>
        <input
          type="password"
          class="form-control"
          id="password"
          v-model="password"
        />
      </div>
      <button type="submit" class="btn btn-primary">Submit</button>
    </form>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "User",
  props: {
    msg: String,
  },
  data() {
    return {
      loading: true,
      firstname: "",
      lastname: "",
      username: "",
      email: "",
      password: "",
      isSuccess: false,
    };
  },
  methods: {
    onSubmit() {
      let data = {
        firstname: this.firstname,
        lastname: this.lastname,
        username: this.username,
        email: this.email,
        password: this.password,
      };
      axios
        .post("http://localhost:8080/api/users/", data, {
          headers: {
            Authorization: `Basic ${btoa("andrinklarer:klarer")}`,
            Accept: "application/json",
          },
        })
        .then((response) => {
          this.isSuccess = response.status == 200 || 201 ? true : false;
          if (this.isSuccess) {
            this.$router.back();
          }
        });
    },
  },
};
</script>
