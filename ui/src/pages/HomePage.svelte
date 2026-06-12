<script lang="ts">
  import {t} from 'i18n'
  import api from 'src/api/api'
  import {navigate} from '@keksworks/svelte-tiny-router'

  let email: string = ''
  let password: string = ''


  async function login() {
    await api.post('users/login', {email, password})
    navigate('/dashboard')
  }

  async function signup() {
    await api.post('users/signup', {email, password})
    navigate('/dashboard')
  }
</script>

<main class="text-center pt-10">
  <form>
    <label for="email">{t.general.email}</label>
    <input id="email" type="email" bind:value={email}>

    <label for="password">{t.general.password}</label>
    <input id="password" type="password" bind:value={password}>

    <button type="button" onclick={login}>{t.general.login}</button>
    <button type="button" onclick={signup}>{t.general.signup}</button>
  </form>
</main>
