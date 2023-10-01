<script>
	import { onMount } from 'svelte';
	import Header from '../lib/layout/Header.svelte';
	import Footer from '../lib/layout/Footer.svelte';
	import Breadcrumb from '$lib/components/Breadcrumb.svelte';
	import './styles.css';
	import '../app.css';
	


</script>

<div class="bg-white dark:bg-gray-800 dark:text-gray-700 transition-colors duration-300 app">
	<Header />
	<!-- <Breadcrumb /> -->
	<div class="mx-auto max-w-6xl">
		<slot>	
		</slot>
	</div>
	<Footer />
</div>

<style>

</style>
