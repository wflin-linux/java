<template>
  <el-form
    ref="ruleForm"
    :model="ruleForm"
    :rules="rules"
    label-width="100px"
    class="demo-ruleForm"
	style="width:500px"
  >
	<el-form-item label="作者" prop="autor">
      <el-input v-model="ruleForm.autor"></el-input>
    </el-form-item>
	
	<el-form-item label="标题" prop="title">
      <el-input v-model="ruleForm.title"></el-input>
    </el-form-item>
	
	<el-form-item label="tags" prop="tags">
      <el-input v-model="ruleForm.tags"></el-input>
    </el-form-item>
    
    <el-form-item label="日期" required>
      <el-col :span="11">
        <el-form-item prop="date1">
          <el-date-picker
            v-model="ruleForm.date1"
            type="date"
            placeholder="Pick a date"
            style="width: 100%"
          ></el-date-picker>
        </el-form-item>
      </el-col>
      <el-col class="line" :span="2">-</el-col>
      <el-col :span="11">
        <el-form-item prop="date2">
          <el-time-picker
            v-model="ruleForm.date2"
            placeholder="Pick a time"
            style="width: 100%"
          ></el-time-picker>
        </el-form-item>
      </el-col>
    </el-form-item>
	
    <el-form-item>
      <el-button type="primary" @click="submitForm('ruleForm')">Create</el-button>
      <el-button @click="resetForm('ruleForm')">Reset</el-button>
    </el-form-item>
  </el-form>
</template>
<script>
export default {
  data() {
    return {
      ruleForm: {
        autor: '',
        title: '',
        tags: '',
        date1: '',
        date2: '',
      },
      rules: {
		autor: [
			{
			  required: true,
			  message: '',
			},
		  ],
        title: [
          {
            required: true,
            message: '',
          },
        ],
		tags: [
		  {
		    required: true,
		    message: '',
		  },
		],
        date1: [
          {
            type: 'date',
            required: true,
            message: 'Please pick a date',
            // trigger: 'blur',
          },
        ],
        date2: [
          {
            type: 'date',
            required: true,
            message: 'Please pick a time',
            // trigger: 'change',
          },
        ],
      },
    }
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          alert('submit!'),
		console.log(this.ruleForm.autor)
		  // axios.get('http://127.0.0.1:8088/Blog/findBlogsByParam', {
		  // 	params: {
				// autor: '',
				// title: '',
				// tags: '',
				// date1: '',
				// date2: '',
		  // 	}
		  // })
		  
		  
		  
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    resetForm(formName) {
      this.$refs[formName].resetFields()
    },
  },
}
</script>