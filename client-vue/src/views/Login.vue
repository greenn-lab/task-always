<template>
  <v-container class="fill-height">
    <v-row align="center" justify="center">
      <v-col
        cols="12"
        md="8"
        lg="4"
      >
        <h1>Task Always</h1>

        <v-form v-model="valid" @submit.prevent="login">
          <v-text-field
            v-model="fields.email"
            label="e-mail"
            prepend-icon="mdi-account"
            validate-on-blur
          />

          <v-text-field
            v-model="fields.pin"
            type="password"
            label="password"
            prepend-icon="mdi-lock"
            validate-on-blur
          />

          <v-btn
            type="submit"
            class="mt-10"
            block
            color="primary"
            :disabled="!valid"
          >
            LOGIN
          </v-btn>

          <v-switch
            v-model="fields.rememberMe"
            label="Remember me"
          />

        </v-form>
      </v-col>
    </v-row>

  </v-container>
</template>

<script>
  export default {
    methods: {
      login() {
        console.log('login')
        this.processing = true
      }
    },
    data() {
      return {
        valid: false,
        fields: {
          email: '',
          pin: '',
          rememberMe: true
        }
      }
    }
  }
</script>
