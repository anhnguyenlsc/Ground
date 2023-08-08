<script>
	import { onMount } from 'svelte';
	import Footer from '../lib/layout/Footer.svelte';
	import Header from '../lib/layout/Header.svelte';
	import './styles.css';

</script>

<div class="bg-white dark:bg-gray-800 dark:text-gray-700 transition-colors duration-300 app">
	<Header />
		<slot>

		</slot>
	<Footer />
</div>

