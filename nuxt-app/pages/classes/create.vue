<template>
  <div class="container">
    <Heading>New Class</Heading>

    <form
      class="mt-4"
      accept-charset="UTF-8"
      v-on:submit.prevent="onSubmit()"
      method="POST"
    >
      <div class="mb-3">
        <label for="classname" class="form-label">Class Name</label>
        <input
          placeholder="BIN40d"
          required
          type="text"
          class="form-control"
          id="classname"
          :class="
            errors.length > 0
              ? errors[0].field == 'classname'
                ? 'error'
                : ''
              : ''
          "
          v-model="classname"
        />
      </div>
      <div class="mb-3">
        <label for="members" class="form-label" aria-describedby="membersHelp"
          >Members</label
        >
        <input
          placeholder="johndoe, annedekten"
          type="text"
          class="form-control"
          id="members"
          v-model="members"
        />
        <div id="membersHelp" class="form-text">
          Enter username of class members seperated with comma
        </div>
      </div>

      <div class="mb-3">
        <label for="subjects" class="form-label" aria-describedby="subjectHelp"
          >Subjects</label
        >
        <input
          placeholder="Math, German"
          type="text"
          class="form-control"
          id="subjects"
          v-model="subjects"
        />
        <div id="subjectHelp" class="form-text">
          Enter names of subject seperated with comma
        </div>
      </div>
      <div class="row" v-if="errors && errors.length">
        <div class="mb3">
          <Alert
            type="danger"
            warning-icon
            v-for="error in errors"
            :key="errors.indexOf(error)"
          >
            {{ error.defaultMessage }}
          </Alert>
        </div>
      </div>
      <button type="submit" class="btn btn-primary">Submit</button>
    </form>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Class",
  props: {
    msg: String,
  },
  data() {
    return {
      loading: true,
      classname: "",
      members: "",
      subjects: "",
      isSuccess: false,
      errors: [],
    };
  },
  methods: {
    onSubmit() {
      let data = {
        classname: this.classname.trim(),
        members: this.members.split(",").map((subject) => subject.trim()),
        subjects: this.subjects.split(",").map((subject) => subject.trim()),
      };

      const base64auth = btoa(`${useUsername.value}:${usePassword.value}`);

      axios
        .post("http://localhost:8080/api/classes/", data, {
          headers: {
            Authorization: `Basic ${base64auth}`,
            Accept: "application/json",
          },
        })
        .then((response) => {
          this.isSuccess = response.status == 200 || 201 ? true : false;
          if (this.isSuccess) {
            this.$router.back();
          } else {
            console.log("error");
          }
        })
        .catch((error) => {
          this.errors = error.response.data.errors;
        });
    },
  },
};
</script>
