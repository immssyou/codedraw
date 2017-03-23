
/*
 * GET home page.
 */
var multer =  require('multer'),
	path = require('path'),
	mime = require('mime'),
	fs = require('fs');

const exec = require('child_process').exec;

var storage = multer.diskStorage({
	destination: function (req, file, callback) {
		callback(null, './uploads');
	},
	filename : function (req, file, callback) {
		callback(null, file.originalname);
	}
});

var upload = multer({ storage: storage }).single('img');

exports.index = function(req, res){
  res.render('index', { title: 'Express' });
};

exports.convert = function(req, res){
	
};

exports.upload = function(req, res) {
	upload(req, res, function(err) {
		console.log(req.file);
		console.log(req.file.originalname);
		var file = req.file.path,
			filename = path.basename(file),
			mimetype = mime.lookup(file);
		
		res.setHeader('Content-disposition', 'attachment; filename='+filename);
		res.setHeader('Content-type', mimetype);
		
		//var filestream = fs.createReadStream(file);
		//filestream.pipe(res);
		
		var filepath = process.cwd() +'/uploads/';
		var cmd = 'converter ' + filepath;
		cmd = cmd + req.file.originalname;
		console.log(cmd);
		setTimeout(() => {
			exec(cmd, function(error, stdout, stderr){
				if(error){
					console.log(stderr);
					return;
					}
				console.log(stdout);
				});
			res.download(process.cwd()+'/test.stl');
		}, 25000);
	});
};

