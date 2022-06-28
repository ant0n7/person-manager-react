<script setup>
const route = useRoute();
const uuid = route.params.id;
const uuid_pattern =
  "/^[0-9a-f]{8}-[0-9a-f]{4}-[0-5][0-9a-f]{3}-[089ab][0-9a-f]{3}-[0-9a-f]{12}$/i";
const username = useCookie('username').value ?? 'default';
const password = useCookie('password').value ?? 'default';
const role = useCookie('role').value ?? 'default';

const base64auth = btoa(`${username}:${password}`);

const { data: student } = await useFetch(
  `http://localhost:8080/api/users/${uuid}`,
  {
    headers: {
      Authorization: `Basic ${base64auth}`,
    },
  }
);
const { data: subjects } = await useFetch(
  `http://localhost:8080/api/subjects/user/${uuid}`,
  {
    headers: {
      Authorization: `Basic ${base64auth}`,
    },
  }
);
const { data: classes } = await useFetch(
  `http://localhost:8080/api/classes/user/${uuid}`,
  {
    headers: {
      Authorization: `Basic ${base64auth}`,
    },
  }
);
</script>

<template>
  <div class="container">
    <Heading
      buttonType="btn-danger"
      buttonText="Delete"
      :buttonAction="deleteUser"
      :role="role"
      >{{ student.firstname + " " + student.lastname }}</Heading
    >

    <h6 class="subtitle student-email">
      <a class="link-primary link-unstyled" :href="'mailto:' + student.email"
        >E-Mail: {{ student.email }}</a
      >
      <p>Username: {{ student.username }}</p>
    </h6>

    <div class="row pt-3" v-if="subjects.length > 0">
      <Heading tag="h2">Subjects</Heading>
      <div
        class="col-md-3 col-12"
        v-for="subject in subjects"
        :key="subject.id"
      >
        <Card :title="subject.subjectname" :link="`/subjects/${subject.id}`" />
      </div>
    </div>

    <div class="row pt-3" v-if="classes.length > 0">
      <Heading tag="h2">Classes</Heading>
      <div
        class="col-md-3 col-12 p-2"
        v-for="appclass in classes"
        :key="appclass.id"
      >
        <Card :title="appclass.classname" :link="`/classes/${appclass.id}`" />
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  methods: {
    deleteUser() {
      axios
        .delete(`http://localhost:8080/api/users/${this.student.id}`, {
          headers: {
            Authorization: `Basic ${btoa("andrinklarer:klarer")}`,
          },
        })
        .then((response) => {
          this.isSuccess = response.status == 200 || 201 ? true : false;
          console.log(this.isSuccess);
          if (this.isSuccess) {
            // location.reload();
            this.$router.back(/*() => {this.$router.afterEach(() => location.reload())}*/);
          }
        });
    },
  },
};
</script>
