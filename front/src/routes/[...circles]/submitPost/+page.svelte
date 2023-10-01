<script lang="ts">
    import { onMount, createEventDispatcher } from 'svelte';
    import axios from 'axios';
    import PostForm from "$lib/components/PostForm.svelte";
    import type { Post } from '$lib/domain';
    import { Storage } from '$lib/support.ts';
    import Editor from '@tinymce/tinymce-svelte';
    import TypeWriter from 'svelte-typewriter';
    import { goto } from '$app/navigation';
    import { page } from '$app/stores';
    
    // @ts-ignore
	let post: Post = {}; 
    let apiKey = "oaiavo7bmnvtht5s2f0gackx9d1nxmke1atcndw9uv1d4mwh";
    let conf = {
      "height": 300,
      "plugins": [
          "a11ychecker","advlist","advcode","advtable","autolink","checklist",
          "lists","link","image","charmap","anchor","searchreplace","visualblocks",
          "powerpaste","fullscreen","formatpainter","insertdatetime","media","table","help","wordcount"
        ],
      "toolbar": "undo redo | a11ycheck casechange blocks | bold italic backcolor | alignleft aligncenter alignright alignjustify | " +
          "bullist numlist checklist outdent indent | removeformat | code table help"
    }

    let currentLocation = Storage.get('currentLocation');
    let currentCircle = '';
    let currentCircleId: any;

    if (typeof localStorage !== 'undefined') {
        //@ts-ignore
        currentCircle = currentLocation.currentCircle;
        //@ts-ignore
        currentCircleId = currentLocation.currentCircleid;
    }

    let communities = [currentCircle];
    let selectedCommunity = communities[0];
    let activeTab = 'Post';
    let url = '';
    let tags = ['AdultsOnly', 'Spoiler', 'NSFW'];
    let selectedTags = [];

    let member = Storage.get('authentication');

    function handleTagClick(tag) {
      if (selectedTags.includes(tag)) {
        selectedTags = selectedTags.filter(t => t !== tag);
      } else {
        selectedTags = [...selectedTags, tag];
      }
    }

    const dispatch = createEventDispatcher();
    let files = [];
    let previewUrls = [];
    const handleFileChange = (event) => {
        files = [...files, ...event.target.files];
        for (const file of event.target.files) {
            const reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = () => {
                previewUrls = [...previewUrls, reader.result];
                dispatch('change', { files });
            };
        }
    };

    const handleDragOver = (event) => {
      event.preventDefault();
    };

    const handleDrop = (event) => {
      event.preventDefault();
      files = [...files, ...event.dataTransfer.files];
      // @ts-ignore
      previewUrls = [...previewUrls, ...Array.from(event.dataTransfer.files).map((file) => URL.createObjectURL(file))];
      dispatch('change', { files });
    };

    const handleRemove = (index) => {
    files = [...files.slice(0, index), ...files.slice(index + 1)];
    previewUrls = [...previewUrls.slice(0, index), ...previewUrls.slice(index + 1)];
    dispatch('change', { files });
  };

  let getOwner = Storage.get('authentication');
  let owner: any;
	if (getOwner === undefined) {} 
    //@ts-ignore 
	else if (getOwner.principal === undefined) {} 
	else {
        //@ts-ignore
		owner = getOwner.principal
    }

  async function postContent() {
    post.tags = selectedTags;
    post.circleId = Number(currentCircleId);
    post.circleName = selectedCommunity.toString();
    post.image = previewUrls;
    post.member = owner;

    const response = await axios({
			method: "POST",
			url: "http://localhost:8080/api/authentication/post/insert",
			data: post
		})

    if (response.data !== null) {
      if (response.data.status === 'success') {
        goto($page.url.pathname.substring(0, $page.url.pathname.lastIndexOf('/')));
      }
    }
  }

</script>

<div class="grid grid-cols-1 md:grid-cols-5 gap-5 mt-10">
    <div class="col-span-1 md:col-span-1">
    </div>
    <div class="col-span-1 md:col-span-3">
        <div class="bg-white rounded-lg p-6 border border-gray-200 shadow-[rgba(0,_0,_0,_0.24)_0px_3px_8px]">
          <TypeWriter>
            <h1 class="text-2xl font-bold mb-4">Create a post</h1>
          </TypeWriter>
            <hr class="border-t border-gray-200 mb-4" />
            <div class="mb-4">
              <label class="block text-gray-700 text-sm font-bold mb-2" for="community">
                Choose a community
              </label>
              <div class="relative">
                <select id="community" bind:value={selectedCommunity} class="block appearance-none w-full bg-gray-200 border border-gray-200 text-gray-700 py-3 px-4 pr-8 rounded leading-tight focus:outline-none focus:bg-white focus:border-gray-500">
                  {#each communities as community}
                    <option>{community}</option>
                  {/each}
                </select>
                <div class="pointer-events-none absolute inset-y-0 right-0 flex items-center px-2 text-gray-700">
                  <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-4 h-4">
                    <path fill-rule="evenodd" d="M12.53 16.28a.75.75 0 01-1.06 0l-7.5-7.5a.75.75 0 011.06-1.06L12 14.69l6.97-6.97a.75.75 0 111.06 1.06l-7.5 7.5z" clip-rule="evenodd" />
                  </svg>        
                </div>
              </div>
            </div>
            <div class="mb-4">
              <ul class="flex border-b">
                <li class="-mb-px mr-1">
                  <button on:click={() => activeTab = 'Post'} class={`bg-white inline-block py-2 px-4 font-semibold ${activeTab === 'Post' ? 'border-l border-t border-r rounded-t text-blue-700' : 'text-blue-500 hover:text-blue-800'}`}>
                    Post
                  </button>
                </li>
                <li class="-mb-px mr-1">
                    <button on:click={() => activeTab = 'Image & Video'} class={`bg-white inline-block py-2 px-4 font-semibold ${activeTab === 'Image & Video' ? 'border-l border-t border-r rounded-t text-blue-700' : 'text-blue-500 hover:text-blue-800'}`}>
                      Image & Video
                    </button>
                  </li>
                  <li class="-mb-px mr-1">
                    <button on:click={() => activeTab = 'Link'} class={`bg-white inline-block py-2 px-4 font-semibold ${activeTab === 'Link' ? 'border-l border-t border-r rounded-t text-blue-700' : 'text-blue-500 hover:text-blue-800'}`}>
                      Link
                    </button>
                  </li>
                  <li class="-mb-px mr-1">
                    <button on:click={() => activeTab = 'Poll'} class={`bg-white inline-block py-2 px-4 font-semibold ${activeTab === 'Poll' ? 'border-l border-t border-r rounded-t text-blue-700' : 'text-blue-500 hover:text-blue-800'}`}>
                      Poll
                    </button>
                  </li>
                </ul>
                <form on:submit|preventDefault={postContent}>
                <div class="p-4">
                  <div class="mb-4">
                    <label class="block text-gray-700 text-sm font-bold mb-2" for="title">
                      Title
                    </label>
                    <input id="title" type="text" placeholder="Title" bind:value={post.title} class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" />
                  </div>
                  {#if activeTab === 'Post'}
                    <div class="mb-4">
                      <label class="block text-gray-700 text-sm font-bold mb-2" for="editor">
                        Content
                      </label>
                      <!-- Rich text editor  -->
                      <Editor {apiKey} {conf} bind:value={post.content} />
                    </div>
                    <div class="mb-4">
                      <!-- svelte-ignore a11y-label-has-associated-control -->
                      <label class="block text-gray-700 text-sm font-bold mb-2">
                        Tags
                      </label>
                      {#each tags as tag}
                        <button on:click={() => handleTagClick(tag)} type="button" class={`mr-2 mb-2 py-1 px-3 rounded-full text-xs font-medium ${selectedTags.includes(tag) ? 'bg-blue-100 text-blue-800' : 'bg-gray-100 text-gray-800'}`}>
                          #{tag}
                        </button>
                      {/each}
                    </div>
                  {:else if activeTab === 'Image & Video'}
                    <!-- Image & Video tab content -->
                    <div
                    class="flex flex-col items-center justify-center p-4 border-2 border-dashed border-gray-400 rounded-md"
                    on:dragover|preventDefault={handleDragOver}
                    on:drop|preventDefault={handleDrop}
                    role="button"
                    tabindex="0"
                    >
                    <div class="flex flex-wrap mb-2">
                      {#each previewUrls as previewUrl, index}
                        <div class="relative w-24 h-24 m-1">
                          <img src={previewUrl} alt="Preview" class="w-full h-full object-cover rounded-md border-2 border-gray-400" />
                          <button
                            class="absolute top-0 right-1 rounded-full text-red-500"
                            on:click={() => handleRemove(index)}
                            >
                            &times;
                          </button>
                          <div class="text-xs text-gray-700 mt-1">
                            {files[index].name} ({Math.round(files[index].size / 1024)} KB)
                          </div>
                        </div>
                      {/each}
                    </div>
                    <label for="file-input" class="cursor-pointer mt-6">
                      <span class="text-gray-700">Click to upload or drag images here</span>
                      <input
                        type="file"
                        id="file-input"
                        class="hidden"
                        accept=".jpg, .jpeg, .png, .svg"
                        multiple
                        on:change={handleFileChange}
                      />
                    </label>
                  </div>
                  {:else if activeTab === 'Link'}
                    <div class="mb-4">
                      <label class="block text-gray-700 text-sm font-bold mb-2" for="url">
                        URL
                      </label>
                      <input id="url" type="text" placeholder="URL" bind:value={url} class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" />
                    </div>
                  {:else if activeTab === 'Poll'}
                    <!-- Poll tab content -->
                  {/if}
                  <div class="flex justify-end">
                    <button class="bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-2 px-4 rounded mr-2" on:click={() => history.back()}>
                      Cancel
                    </button>
                    <!-- <button class="bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-2 px-4 rounded mr-2">
                      Save Draft
                    </button> -->
                    <button type="submit" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
                      Post
                    </button>
                  </div>

                </div>
                </form>
              </div>
          </div>
    </div>
</div>
