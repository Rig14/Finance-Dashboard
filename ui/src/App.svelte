<script lang="ts">
  import {t} from 'i18n'
  import Toasts from 'src/components/Toasts.svelte'
  import {Route, Router} from '@keksworks/svelte-tiny-router'
  import HomePage from 'src/pages/HomePage.svelte'
  import SessionPage from 'src/pages/SessionPage.svelte'
  import DashboardPage from 'src/pages/dashboard/DashboardPage.svelte'
  import {initSession, user} from './stores/auth'
  import api from 'src/api/api'
  import type {User} from 'src/api/types'
  import {onMount} from 'svelte'

  onMount(async () =>  {
    if (!$user) {
      const user = await api.get<User>('users/user')
      initSession(user)
    }
  })
</script>

<svelte:head>
  <title>{t.title}</title>
</svelte:head>

<Toasts/>

<Router>
  <Route path="/" component={HomePage}/>
  <Route path="/dashboard" component={DashboardPage}/>
  <Route path="/session" component={SessionPage}/>
</Router>
