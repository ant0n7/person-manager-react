<template>
  <div class="container">
    <Heading>New Subject</Heading>
    <form
      class="mt-4"
      accept-charset="UTF-8"
      v-on:submit.prevent="onSubmit()"
      method="POST"
    >
      <div class="mb-3">
        <label for="subjectname" class="form-label">Subject Name</label>
        <Alert
          type="danger"
          warning-icon
          v-if="errors.some((x) => x.field == 'subjectname')"
        >
          {{ errors.find((x) => x.field == "subjectname").defaultMessage }}
        </Alert>
        <input
          required
          placeholder="e.g. Math"
          type="text"
          class="form-control"
          id="subjectname"
          :class="
            errors.length > 0
              ? errors.some((x) => x.field == 'subjectname')
                ? 'error'
                : ''
              : ''
          "
          v-model="subjectname"
        />
      </div>
      <div class="success" v-if="isSuccess">
        We received your submission, thank you!
      </div>
      <button type="submit" class="btn btn-primary">Submit</button>
    </form>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Subject",
  props: {
    msg: String,
  },
  data() {
    return {
      loading: true,
      subjectname: "",
      isSuccess: false,
      errors: [],
    };
  },
  methods: {
    onSubmit() {
      let data = {
        subjectname: this.subjectname,
      };
      axios
        .post("http://localhost:8080/api/subjects/", data, {
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
        })
        .catch((error) => {
          this.errors = error.response.data.errors;
        });
    },
  },
};
</script>
