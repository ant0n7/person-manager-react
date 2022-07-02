<template>
  <div class="container">
    <!-- <h1>Students <button class="btn btn-primary btn-md mb-2 ms-auto float-end" type="button">Create</button></h1> -->
    <Heading buttonText="Create" buttonLink="/students/create" :role="role">People</Heading>

    <div v-if="pending">
      <Alert type="info"> Loading... {{ pending }} </Alert>
    </div>

    <div class="row pt-3" v-if="members && students.length > 0">
      <Heading class="mt-3" tag="h2">Students</Heading>
      <div
        class="col-md-3 col-12 p-2"
        v-for="student in students"
        :key="student.id"
      >
        <Card
          :title="student.firstname + ' ' + student.lastname"
          :subtitle="student.email"
          :link="`/students/${student.id}`"
        />
      </div>
    </div>

    <div class="row pt-3" v-if="members && teachers.length > 0">
      <Heading class="mt-3" tag="h2">Teachers</Heading>
      <div
        class="col-md-3 col-12 p-2"
        v-for="teacher in teachers"
        :key="teacher.id"
      >
        <Card
          :title="teacher.firstname + ' ' + teacher.lastname"
          :subtitle="teacher.email"
          :link="`/students/${teacher.id}`"
        />
      </div>
    </div>

    <div class="row" v-if="!members">
      <div class="col-12">
        <Alert type="warning" warning-icon> No students found. Make sure you are logged in!</Alert>
      </div>
    </div>
  </div>
</template>

<script setup>
const username = useCookie('username').value ?? 'default';
const password = useCookie('password').value ?? 'default';
const role = useCookie('role').value ?? 'default';

const base64auth = btoa(`${username}:${password}`);
// const base64auth = btoa(`andrinklarer:klarer`);

const { pending, data: members } = await useAsyncData("students", () =>
  $fetch("http://localhost:8080/api/users/", {
    headers: {
      Authorization: `Basic ${base64auth}`,
    },
  })
);
</script>
<script>
export default {
  computed: {
    students() {
      return this.members.filter((member) => {
        return member.roles.some((role) => role.rolename === "STUDENT");
      });
    },
    teachers() {
      return this.members.filter((member) => {
        return member.roles.some((role) => role.rolename === "TEACHER");
      });
    },
    role() {
      return useCookie('role').value;
      // return 'TEACHER';
    }
  },
};
</script>
