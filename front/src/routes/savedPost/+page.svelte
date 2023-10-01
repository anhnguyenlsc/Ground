<script>
	import { onMount } from "svelte";
    import { Storage } from '$lib/support.ts';
    import axios from "axios";

    let posts = [];
    let getOwner = Storage.get('authentication');
    let owner;
	if (getOwner === undefined) {} 
    //@ts-ignore 
	else if (getOwner.principal === undefined) {} 
	else {
        //@ts-ignore
		owner = getOwner.principal
	}

    onMount(async () => {
        const savedPostsApi = 'http://localhost:8080/api/authentication/collections/all/' + owner.id;
        const response = await axios.get(savedPostsApi);
        const savedPosts = response.data;

        posts = savedPosts.map((sp) => ({
            id: sp.id, 
            title: sp.title,
            memberId: sp.memberId,
            savedPosts: sp.savedPosts

        }));
    })
</script>

<div class="grid grid-cols-1 md:grid-cols-4 gap-5 mt-10">
    <!-- save collections  -->
    <div class="col-span-1 md:col-span-1">
        <div class="mb-5 p-4 rounded-xl shadow-[rgba(0,_0,_0,_0.24)_0px_3px_8px] dark:bg-gray-700">
            <div>
                <h1 class="font-bold text-xl cursor-pointer">
                    <span>
                        <a href="/savedPost">Saved Posts</a>
                    </span>
                </h1>
                <hr class="my-2"/>
                <h2 class="font-semibold">My Collections</h2>
                <!-- Collection items -->
                <div class="grid grid-cols-1 gap-2 mt-2">
                    <!-- Each collection item -->
                    <div class="flex items-center gap-2 mb-1">
                        <img src="https://images.igdb.com/igdb/image/upload/t_cover_big/co6ljw.png" alt="collection_image" class="w-10 h-10 object-cover rounded-md"/>
                        <h3 class="font-semibold">Game Animation</h3>
                    </div>
                    <div class="flex items-center gap-2 mb-1">
                        <img src="https://images.igdb.com/igdb/image/upload/t_cover_big/co4ha1.png" alt="collection_image" class="w-10 h-10 object-cover rounded-md"/>
                        <h3 class="font-semibold">Movies</h3>
                    </div>
                    <div class="flex items-center gap-2 mb-1">
                        <img src="https://images.igdb.com/igdb/image/upload/t_cover_big/co70b8.png" alt="collection_image" class="w-10 h-10 object-cover rounded-md"/>
                        <h3 class="font-semibold">Something...</h3>
                    </div>
                </div>

                <!-- Create New Collection Button -->
                <button href="#_" class="mt-4 px-5 py-2 w-full bg-blue-50 hover:bg-blue-100 hover:text-blue-600 text-blue-500 rounded-lg text-base text-center font-medium flex items-center justify-center">
                    <img src="https://res.cloudinary.com/dzgdczkjk/image/upload/v1694763642/collection_ye8gza.png" alt="collection" class="w-6 h-6 inline-block mr-2">
                    Create new collection
                </button>
            </div>
        </div>
    </div>

    <!-- saved post -->
    <div class="col-span-1 md:col-span-3">
        {#each posts as post (post.id)}            
                {#each post.savedPosts as savedPostItem (savedPostItem.id)}
                    <div class="mb-5 rounded-xl shadow-[rgba(0,_0,_0,_0.24)_0px_3px_8px] dark:bg-gray-700">
                        <div>
                            <div class="flex items-start space-x-4 p-4 w-30 h-30">
                                <!-- Image -->
                                <img src="{savedPostItem.image}" alt="{savedPostItem.title}" class="w-40 h-40 rounded-md">
                                <div>
                                    <!-- Title -->
                                    <h2 class="font-bold text-lg mb-2">{savedPostItem.title}</h2>
                            
                                    <!-- Saved to Collection -->
                                    <p class="text-sm mb-2">Post - Saved to 
                                        <span class="font-semibold">{post.title}</span>
                                    </p>
                            
                                    <!-- Avatar and Saved from -->
                                    <div class="flex items-center w-full h-auto space-x-2 mb-4">
                                        <!-- <img src="{}" alt="Avatar" class="w-8 h-8 rounded-full"> -->
                                        <p class="text-s">Saved from 
                                            <a href="/circles/{savedPostItem.circleId}" class="text-blue-500 italic font-medium">{savedPostItem.circleName}</a>
                                        </p>
                                    </div>
                            
                                    <!-- Buttons -->
                                    <div class="flex space-x-2">
                                        <button class="px-4 py-2 rounded-md bg-blue-500 text-white">Save to Collection</button>
                                        <button class="px-4 py-2 rounded-md bg-green-500 text-white">
                                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
                                                <path stroke-linecap="round" stroke-linejoin="round" d="M7.217 10.907a2.25 2.25 0 100 2.186m0-2.186c.18.324.283.696.283 1.093s-.103.77-.283 1.093m0-2.186l9.566-5.314m-9.566 7.5l9.566 5.314m0 0a2.25 2.25 0 103.935 2.186 2.25 2.25 0 00-3.935-2.186zm0-12.814a2.25 2.25 0 103.933-2.185 2.25 2.25 0 00-3.933 2.185z" />
                                            </svg>                                  
                                        </button>
                                        <button class="px-4 py-2 rounded-md bg-red-500 text-white">
                                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
                                                <path stroke-linecap="round" stroke-linejoin="round" d="M3 3l1.664 1.664M21 21l-1.5-1.5m-5.485-1.242L12 17.25 4.5 21V8.742m.164-4.078a2.15 2.15 0 011.743-1.342 48.507 48.507 0 0111.186 0c1.1.128 1.907 1.077 1.907 2.185V19.5M4.664 4.664L19.5 19.5" />
                                            </svg>                                  
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                {/each}
        {/each}
    </div>
</div>