<template>
  <div class="container">
    <Heading buttonText="Create" buttonLink="/students/create" :role="role">People</Heading>

    <div v-if="pending">
      <Alert type="info"> Loading... {{ pending }} </Alert>
    </div>

    <div class="row pt-3" v-if="useMembers().value && students.length > 0">
      <Heading class="mt-3" tag="h3">Students</Heading>
      <div
        class="col-lg-3 col-md-4 col-sm-6 col-12 p-2"
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

    <div class="row pt-3" v-if="useMembers().value && teachers.length > 0">
      <Heading class="mt-3" tag="h3">Teachers</Heading>
      <div
        class="col-lg-3 col-md-4 col-sm-6 col-12 p-2"
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

    <div class="row" v-if="!useMembers().value">
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

const { pending, data: members } = await useAsyncData("students", () =>
  $fetch("http://localhost:8080/api/users/", {
    headers: {
      Authorization: `Basic ${base64auth}`,
    },
  })
);
useMembers().value = members;
const members2 = useMembers();
</script>
<script>
export default {
  computed: {
    students() {
      return useMembers().value?.filter((member) => {
        return member.roles.some((role) => role.rolename === "STUDENT");
      });
    },
    teachers() {
      return useMembers().value?.filter((member) => {
        return member.roles.some((role) => role.rolename === "TEACHER");
      });
    },
    role() {
      return useCookie('role').value;
    }
  },
};
</script>
