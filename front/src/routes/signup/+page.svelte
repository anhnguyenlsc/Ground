<script lang="ts">
	import axios from "axios";
	import type { User } from "../../lib/domain/index";
	import { goto } from "$app/navigation";
	// @ts-ignore
	let user: User = {}; 

	async function signUp() {
		const randomNum = Math.floor(Math.random() * 100);
        const avt_API = 'https://api.dicebear.com/6.x/bottts-neutral/svg?seed=' + randomNum;
		user.avatar = avt_API;
		
		const response = await axios({
			method: "POST",
			url: "http://localhost:8080/api/authentication/signup",
			data: user
		})

		if (response.data !== null) {
			if (response.data.status === "failed") {
				//TODO : Pop up a notification "Email is already existed"
				console.log('Sign up failed');
			}
			else {
				sessionStorage.setItem('token', response.data.token);
              	sessionStorage.setItem('avatar', avt_API);
				//Navigate to Email Verification Page
				location.href = "http://localhost:5173/verification";
			}
		}
	}

</script>

<svelte:head>
	<title>Sign up</title>
	<meta name="description" content="Sign Up Page" />
</svelte:head>


<div class="min-h-screen py-6 flex flex-col justify-center sm:py-12">
	<div class="w-full relative py-3 sm:max-w-lg sm:mx-auto">
		<div
			class="absolute inset-0 bg-gradient-to-r from-blue-300 to-blue-600 shadow-lg transform -skew-y-6 sm:skew-y-0 sm:-rotate-6 sm:rounded-3xl">
		</div>
		<div class="w-full relative px-4 py-10 bg-white shadow-lg sm:rounded-3xl">
			<div class="max-w-md mx-auto">
				<div class="space-y-4 md:space-y-6 sm:p-8">
          <a href="/">
            <img class="mx-auto h-10 w-auto" src="/logo.png" alt="logo">
          </a>
          <h1 class="mt-6 text-center text-xl font-bold sm:text-2xl">
            Welcome to Ground
          </h1>

			<div class="relative mt-7">
				<div class="absolute inset-0 flex items-center">
					<div class="w-full border-t border-gray-500/30 dark:border-gray-500/70"></div>
				</div>
				<div class="relative flex justify-center text-sm">
					<span class="bg-white px-2 text-gray-500">GROUND ACCOUNT REGISTER</span>
				</div>
			</div>
			<form class="space-y-4 md:space-y-6" on:submit|preventDefault={signUp}> 
				<div>
					<label for="id" class="block mb-2 text-sm font-medium text-gray-700 ">Id</label>
					<input type="text" bind:value={user.userId} name="id" id="id" class="block w-full rounded-md border border-gray-500/30 px-3 py-2 text-sm placeholder-gray-300/80 shadow-sm focus:border-gray-500 focus:outline-none focus:ring-0 sm:text-base"  placeholder="Please enter within 4-15 characters" required="">
				</div>
				<div>
					<label for="password" class="block mb-2 text-sm font-medium text-gray-700 ">Password</label>
					<input type="password" bind:value={user.password} name="password" id="password" placeholder="At least 6 characters (alphabets, numbers required)" class="block w-full rounded-md border border-gray-500/30 px-3 py-2 text-sm placeholder-gray-300/80 shadow-sm focus:border-gray-500 focus:outline-none focus:ring-0 sm:text-base" required="">
				</div>
				<div>
					<label for="email" class="block mb-2 text-sm font-medium text-gray-700 ">Email</label>
					<input type="email" bind:value={user.email} name="email" id="email" placeholder="nemokin@ground.com" class="block w-full rounded-md border border-gray-500/30 px-3 py-2 text-sm placeholder-gray-300/80 shadow-sm focus:border-gray-500 focus:outline-none focus:ring-0 sm:text-base" required="">
				</div>
				<div>
					<label for="name" class="block mb-2 text-sm font-medium text-gray-700 ">Name</label>
					<input type="text" bind:value={user.name} name="name" id="name" placeholder="Full name" class="block w-full rounded-md border border-gray-500/30 px-3 py-2 text-sm placeholder-gray-300/80 shadow-sm focus:border-gray-500 focus:outline-none focus:ring-0 sm:text-base" required="">
				</div>
				<div>
					<label for="nickname" class="block mb-2 text-sm font-medium text-gray-700 ">Nick name</label>
					<input type="text" bind:value={user.nickname} name="nickname" id="nickname" placeholder="Please enter your nickname in 20 alphabets" class="block w-full rounded-md border border-gray-500/30 px-3 py-2 text-sm placeholder-gray-300/80 shadow-sm focus:border-gray-500 focus:outline-none focus:ring-0 sm:text-base" required="">
				</div>
			
            <div class="flex items-center justify-center">
                <div>
                    <div>
                        <div style="width: 304px; height: 78px;">
                            <div>
                                 <iframe title="reCAPTCHA" src="https://www.google.com/recaptcha/api2/anchor?ar=1&amp;k=6Lcvw_gSAAAAAH3zOofJBJOFLpmjx7Vq3hxnYIRw&amp;co=aHR0cHM6Ly9va2t5LmtyOjQ0Mw..&amp;hl=en&amp;type=image&amp;v=iZWPJyR27lB0cR4hL_xOX0GC&amp;theme=light&amp;size=normal&amp;badge=bottomright&amp;cb=vtynw16etd6s" width="304" height="78" role="presentation" name="a-uxmyyujy1fpt" frameborder="0" scrolling="no" sandbox="allow-forms allow-popups allow-same-origin allow-scripts allow-top-navigation allow-modals allow-popups-to-escape-sandbox allow-storage-access-by-user-activation"></iframe>
                            </div>
                            <textarea id="g-recaptcha-response-1" name="g-recaptcha-response" class="g-recaptcha-response" style="width: 250px; height: 40px; border: 1px solid rgb(193, 193, 193); margin: 10px 25px; padding: 0px; resize: none; display: none;"></textarea>
                        </div>
                        <iframe style="display: none;"></iframe>
                    </div>
                </div>
            </div>
            <div>
                <button type="submit" class="flex w-full justify-center rounded-md border border-transparent bg-blue-600 px-4 py-2 text-sm font-medium text-white hover:bg-blue-400 focus:outline-none disabled:bg-blue-500 disabled:opacity-40" disabled="">Sign up</button>
            </div>
            <p class="flex items-center justify-center space-x-1 text-sm">
                <span>Already have account?</span>
                <a class="flex items-center font-medium text-blue-400 hover:underline" href="/login">Sign in</a>
            </p>
			</form>
				</div>
			</div>
		</div>
	</div>
</div>
