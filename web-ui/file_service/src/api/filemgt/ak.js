import request from '@/utils/request'

export default {
    getAKs(current, limit){
        return request({
            url: `/filemgt/accesskey/page/${current}/${limit}`,
            method: 'get'
        })
    },
    applyAK(){
        return request({
            url: `/filemgt/accesskey/ak`,
            method: 'post'
        })
    },
    deteleAKById(id){
        return request({
            url: `/filemgt/accesskey/${id}`,
            method: 'delete'
        })
    }

}