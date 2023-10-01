import axios from 'axios';

export async function load({params}) {
    const circleApi = 'http://localhost:8080/api/authentication/circle/all/' + params.types.toString();

    const response = await axios.get(circleApi);
    const circleData = response.data;
    
    const circleItems = circleData.map((cl: any) => ({
        id: cl.id,
    }))

    const getPostsApi = 'http://localhost:8080/api/authentication/post/';

    const postsData = (await Promise.all(circleItems.map(async (item: any) => {
        const postResponse = await axios.get(getPostsApi + item.id);
        return postResponse.data;
    }))).flat();

    postsData.sort((a, b) => new Date(b.createdAt).getTime() - new Date(a.createdAt).getTime());
    
    return {
        summaries: circleData.map((cr: { id: any; name: any; description: any; rules: any; owner:any; membersCounts:any; tags: any; avatar: any; createdAt: any; }) => ({
            c_id: cr.id,
            c_name: cr.name,
            c_des: cr.description,
            c_rules: cr.rules,
            c_owner: cr.owner,
            c_membersCounts: cr.membersCounts,
            c_tags: cr.tags,
            c_avatar: cr.avatar,
            c_createdAt: cr.createdAt
        })),
        
        posts: postsData.map((p: {id: any; title: any; content: any; circleId: any; circleName: any; image: any; member: any; from:any; likes: any; share: any; comment: any; createdAt: any;}) => ({
            p_id: p.id,
            p_title: p.title,
            p_content: p.content,
            p_circleId: p.circleId,
            p_circleName: p.circleName,
            p_image: p.image,
            p_member: p.member,
            p_from: p.from,
            p_likes: p.likes,
            p_share: p.share,
            p_comments: p.comment,
            p_createdAt: p.createdAt
        })) 
    }
}