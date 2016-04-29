module.exports = function(grunt) {

	grunt.initConfig({
		pkg: grunt.file.readJSON('package.json'),
		uglify : {
			options: {
			      compress: {
			        drop_console: true
			      },
			},
			build : {
				src : 'src/main/resources/static/js/boardScriptForm.js',
				dest : 'src/main/resources/static/js/boardScriptForm.min.js'
			},
			build : {
				src : 'src/main/resources/static/js/boardScriptFormPop.js',
				dest : 'src/main/resources/static/js/boardScriptFormPop.min.js'
			},
			build : {
				src : 'src/main/resources/static/js/boardScriptList.js',
				dest : 'src/main/resources/static/js/boardScriptList.min.js'
			},
			build : {
				src : 'src/main/resources/static/js/boardScriptView.js',
				dest : 'src/main/resources/static/js/boardScriptView.min.js'
			}
		}
	});

	grunt.loadNpmTasks('grunt-contrib-uglify');

	grunt.registerTask('default', ['uglify']);
};