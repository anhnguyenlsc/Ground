<script>
	import axios from "axios";
	import { onMount } from "svelte";

    // const token = sessionStorage.getItem('token');
    // onMount(async ()  => {
    //         const response = await axios({
    //         headers: {
    //             "Content-Type": "text/plain"
    //         },
    //         method: "POST",
    //         url: "http://localhost:8080/api/authentication/viewProfile",
    //         data: token
    //     })
    // })
        

    let activeTab = 'Profile';
    let name = '';
    let nickname = '';
    let gender = '';
    let email = '';
    let phone = '';
    let address = '';
    let bio = '';
    let location = '';

    let currentPass = '';
    let newPass = '';
    let confirmPass = '';
</script>
  
<div class="p-16 pt-4">
    <h1 class="text-3xl font-bold mb-4">Account Settings✨</h1>
    <!-- Menu tabs -->
    <div class="flex mb-4 border-b border-gray-200">
        <button class="py-2 mr-10 rounded-md relative" class:font-bold={activeTab === 'Profile'} on:click={() => (activeTab = 'Profile')}>
            Profile
            {#if activeTab === 'Profile'}
                <span class="absolute bottom-0 left-0 w-full h-0.5 bg-blue-500 transition-all duration-300"/>
            {/if}
        </button>
        <button class="py-2 mr-10 rounded-md relative" class:font-bold={activeTab === 'Password'} on:click={() => (activeTab = 'Password')}>
            Password
            {#if activeTab === 'Password'}
                <span
                class="absolute bottom-0 left-0 w-full h-0.5 bg-blue-500 transition-all duration-300"
                />
            {/if}
        </button>
        <button class="py-2 mr-10 rounded-md relative" class:font-bold={activeTab === 'Notification'} on:click={() => (activeTab = 'Notification')}>
            Notification
            {#if activeTab === 'Notification'}
                <span
                class="absolute bottom-0 left-0 w-full h-0.5 bg-blue-500 transition-all duration-300"
                />
            {/if}
        </button>
        <button class="py-2 mr-10 rounded-md relative" class:font-bold={activeTab === 'Payment'} on:click={() => (activeTab = 'Payment')} >
            Payment
            {#if activeTab === 'Payment'}
                <span
                class="absolute bottom-0 left-0 w-full h-0.5 bg-blue-500 transition-all duration-300"
                />
            {/if}
        </button>
    </div>
    {#if activeTab === 'Profile'}
    <div>
        <h1 class="text-xl font-bold mb-4">Profile</h1>
        <div class="flex flex-wrap">
            <div class="w-full flex items-center mb-4">
                <img class="w-40 h-40 rounded-full mr-4" src="https://api.dicebear.com/6.x/bottts-neutral/svg?seed=77" alt="avatar"/>
                <button class="px-4 py-2 rounded-md bg-blue-500 hover:bg-blue-600 text-white">Update</button>
            </div>

            <div class="w-full flex items-center mb-4">
                <label for="name" class="w-1/3 font-bold">Name</label>
                <input class="block w-full rounded-md border border-gray-500/30 px-3 py-2 text-sm placeholder-gray-500/80 shadow-sm focus:border-gray-500 focus:outline-none focus:ring-0 dark:bg-gray-500/20 sm:text-base" type="text" id="name" 
                    bind:value={name}/>
            </div>
            <div class="mt-2 w-full flex items-center mb-4">
                <label for="nickname" class="w-1/3 font-bold">Nickname</label>
                <input class="block w-full rounded-md border border-gray-500/30 px-3 py-2 text-sm placeholder-gray-500/80 shadow-sm focus:border-gray-500 focus:outline-none focus:ring-0 dark:bg-gray-500/20 sm:text-base" type="text" id="nickname" 
                    bind:value={nickname}/>
            </div>
            <div class="mt-2 w-full flex items-center mb-4">
                <label for="gender" class="w-1/3 font-bold">Gender</label>
                <select class="block w-full rounded-md border border-gray-500/30 px-3 py-2 text-sm placeholder-gray-500/80 shadow-sm focus:border-gray-500 focus:outline-none focus:ring-0 dark:bg-gray-500/20 sm:text-base" id="gender" 
                    bind:value={gender}>
                    <option value="" disabled selected>Select Gender</option>
                    <option value="Male">Male</option>
                    <option value="Female">Female</option>
                    <option value="Other">Other</option>
                </select>
            </div>
            <div class="mt-2 w-full flex items-center mb-4">
                <label for="email" class="w-1/3 font-bold">Email</label>
                <input class="block w-full rounded-md border border-gray-500/30 px-3 py-2 text-sm placeholder-gray-500/80 shadow-sm focus:border-gray-500 focus:outline-none focus:ring-0 dark:bg-gray-500/20 sm:text-base" type="email" id="email" 
                    bind:value={email}/>
            </div>
            <div class="mt-2 w-full flex items-center mb-4">
                <label for="phone" class="w-1/3 font-bold">Phone</label>
                <input class="block w-full rounded-md border border-gray-500/30 px-3 py-2 text-sm placeholder-gray-500/80 shadow-sm focus:border-gray-500 focus:outline-none focus:ring-0 dark:bg-gray-500/20 sm:text-base" type="tel" id="phone" 
                    bind:value={phone}/>
            </div>
            <div class="mt-2 w-full flex items-center mb-4">
                <label for="address" class="w-1/3 font-bold">Address</label>
                <input class="block w-full rounded-md border border-gray-500/30 px-3 py-2 text-sm placeholder-gray-500/80 shadow-sm focus:border-gray-500 focus:outline-none focus:ring-0 dark:bg-gray-500/20 sm:text-base" type="text" id="adrress" 
                    bind:value={address}/>
                
            </div>
            <div class="mt-2 w-full flex items-center mb-4">
                <label for="bio" class="w-1/3 font-bold">Bio</label>
                <textarea class="block w-full rounded-md border border-gray-500/30 px-3 py-2 text-sm placeholder-gray-500/80 shadow-sm focus:border-gray-500 focus:outline-none focus:ring-0 dark:bg-gray-500/20 sm:text-base" id="bio" 
                    bind:value={bio} rows="3"/>
            </div>
            <div class="mt-2 w-full flex items-center mb-4">
                <label for="location" class="w-1/3 font-bold">Location</label>
                <input type="text" class="block w-full rounded-md border border-gray-500/30 px-3 py-2 text-sm placeholder-gray-500/80 shadow-sm focus:border-gray-500 focus:outline-none focus:ring-0 dark:bg-gray-500/20 sm:text-base" id="location" 
                    bind:value={location}/>
            </div>
            <div class="mt-2 w-full flex justify-end">
                <button class="px-4 py-2 rounded-md bg-blue-500 hover:bg-blue-600 text-white">Save</button>
            </div>
        </div>
        </div>
    {:else if activeTab === 'Password'}
        <h1 class="text-xl font-bold mb-4">Password</h1>
        <div class="flex flex-wrap">
            <div class="w-full flex items-center mb-4">
                <label for="currentPass" class="w-1/3 font-bold">Current Password</label>
                <input class="block w-full rounded-md border border-gray-500/30 px-3 py-2 text-sm placeholder-gray-500/80 shadow-sm focus:border-gray-500 focus:outline-none focus:ring-0 dark:bg-gray-500/20 sm:text-base" type="text" id="name" 
                    bind:value={currentPass}/>
            </div>
            <div class="w-full flex items-center mb-4">
                <label for="newPass" class="w-1/3 font-bold">New Password</label>
                <input class="block w-full rounded-md border border-gray-500/30 px-3 py-2 text-sm placeholder-gray-500/80 shadow-sm focus:border-gray-500 focus:outline-none focus:ring-0 dark:bg-gray-500/20 sm:text-base" type="text" id="name" 
                    bind:value={newPass}/>
            </div>
            <div class="w-full flex items-center mb-4">
                <label for="confirmPass" class="w-1/3 font-bold">Confirm Password</label>
                <input class="block w-full rounded-md border border-gray-500/30 px-3 py-2 text-sm placeholder-gray-500/80 shadow-sm focus:border-gray-500 focus:outline-none focus:ring-0 dark:bg-gray-500/20 sm:text-base" type="text" id="name" 
                    bind:value={confirmPass}/>
            </div>
            <div class="mt-2 w-full flex justify-end">
                <button class="px-4 py-2 rounded-md bg-blue-500 hover:bg-blue-600 text-white">Save</button>
            </div>
        </div>
    {:else if activeTab === 'Notification'}
        <h1 class="text-xl font-bold mb-4">Notification</h1>
    {:else if activeTab === 'Payment'}
        <h1 class="text-xl font-bold mb-4">Payment</h1>
    {/if}
</div>
  
  