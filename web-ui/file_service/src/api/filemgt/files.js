import request from '@/utils/request'

export default {
    getFiles(current, limit, fileQuery){
        return request({
            url: `/filemgt/files/page/${current}/${limit}`,
            method: 'post',
            data: fileQuery
        })
    },
    deleteFileById(id){
        return request({
            url: `/filemgt/files/${id}`,
            method: 'delete'
        })
    },
    addFiles(files){
        return request({
            url: `/filemgt/files/fileinfo`,
            method: 'post',
            data: files
        })
    },
    getFileById(id){
        return request({
            url: `/filemgt/files/fileinfo/${id}`,
            method: 'get'
        })
    },
    updateFileById(files){
        return request({
            url: `/filemgt/files/file`,
            method: 'put',
            data: files
        })
    }

}