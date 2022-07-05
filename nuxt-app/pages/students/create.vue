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
        <Alert
          type="danger"
          warning-icon
          v-if="errors.some((x) => x.field == 'firstname')"
        >
          {{ errors.find((x) => x.field == "firstname").defaultMessage }}
        </Alert>
        <input
          placeholder="John"
          required
          type="text"
          class="form-control"
          id="firstname"
          :class="
            errors.length > 0
              ? errors.some((x) => x.field == 'firstname')
                ? 'error'
                : ''
              : ''
          "
          v-model="firstname"
        />
      </div>
      <div class="mb-3">
        <label for="lastname" class="form-label">Last Name</label>
        <Alert
          type="danger"
          warning-icon
          v-if="errors.some((x) => x.field == 'lastname')"
        >
          {{ errors.find((x) => x.field == "lastname").defaultMessage }}
        </Alert>
        <input
          placeholder="Doe"
          required
          type="text"
          class="form-control"
          id="lastname"
          :class="
            errors.length > 0
              ? errors.some((x) => x.field == 'lastname')
                ? 'error'
                : ''
              : ''
          "
          v-model="lastname"
        />
      </div>
      <div class="mb-3">
        <label for="username" class="form-label">Username</label>
        <Alert
          type="danger"
          warning-icon
          v-if="errors.some((x) => x.field == 'username')"
        >
          {{ errors.find((x) => x.field == "username").defaultMessage }}
        </Alert>
        <input
          placeholder="johndoe"
          required
          type="text"
          class="form-control"
          id="username"
          :class="
            errors.length > 0
              ? errors.some((x) => x.field == 'username')
                ? 'error'
                : ''
              : ''
          "
          v-model="username"
        />
      </div>
      <div class="mb-3">
        <label for="email" class="form-label">Email</label>
        <Alert
          type="danger"
          warning-icon
          v-if="errors.some((x) => x.field == 'email')"
        >
          {{ errors.find((x) => x.field == "email").defaultMessage }}
        </Alert>
        <input
          placeholder="john@student.com"
          required
          type="email"
          class="form-control"
          id="email"
          :class="
            errors.length > 0
              ? errors.some((x) => x.field == 'email')
                ? 'error'
                : ''
              : ''
          "
          v-model="email"
        />
      </div>
      <div class="mb-3">
        <label for="password" class="form-label" aria-describedby="passwordHelp"
          >Password</label
        >
        <Alert
          type="danger"
          warning-icon
          v-if="errors.some((x) => x.field == 'password')"
        >
          {{ errors.find((x) => x.field == "password").defaultMessage }}
        </Alert>
        <input
          placeholder="Choose a safe password"
          required
          type="password"
          class="form-control"
          id="password"
          :class="
            errors.length > 0
              ? errors.some((x) => x.field == 'password')
                ? 'error'
                : ''
              : ''
          "
          v-model="password"
        />
        <div id="passwordHelp" class="form-text">At least four characters</div>
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
      errors: [],
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

      const base64auth = btoa(`${useUsername.value}:${usePassword.value}`);

      axios
        .post("http://localhost:8080/api/users/", data, {
          headers: {
            Authorization: `Basic ${base64auth}`,
            Accept: "application/json",
          },
        })
        .then((response) => {
          this.isSuccess = response.status == 200 || 201 ? true : false;
          if (this.isSuccess) {
            this.$router.back();
          }
        })
        .catch((error) => {
          this.errors = error.response.data.errors;
        });
    },
  },
};
</script>
